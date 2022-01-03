package com.changgou.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.changgou.goods.feign.SkuFeign;
import com.changgou.goods.pojo.Sku;
import com.changgou.search.dao.SkuEsMapper;
import com.changgou.search.pojo.SkuInfo;
import com.changgou.search.service.SkuService;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: saijie
 * @date: 2022/1/2 20:48
 */
@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private SkuFeign skuFeign;
    @Autowired
    private SkuEsMapper skuEsMapper;

    /**
     * 导入sku数据到es
     */
    @Override
    public void importSku() {
        //1.先根据商品微服务feign 查询符合条件的sku的列表数据
        //1.1 service-goods-api创建一个feign接口
        //1.2 编写一个类 加入一个注解@feignclent(name="goods")
        //1.3 编写一个方法：查询符合条件的sku的列表数据
        //1.4 在service-goods微服务中实现controller-service-dao的调用。相当于实现feign接口

        //1.5 添加依赖 ，开启feignclients
        //1.6 注入feign 调用feign
        Result<List<Sku>> result = skuFeign.findByStatus("1");
        List<Sku> skuList = result.getData();
        if (skuList != null && skuList.size() > 0) {
            //转换将List<Sku> 转换成List<SkuInfo>
            List<SkuInfo> skuInfoList = JSON.parseArray(JSON.toJSONString(skuList), SkuInfo.class);
            //设置规格的数据到specMap属性中
            for (SkuInfo skuInfo : skuInfoList) {
                String spec = skuInfo.getSpec();//{"电视音响效果":"环绕","电视屏幕尺寸":"20英寸","尺码":"165"}
                skuInfo.setSpecMap(JSON.parseObject(spec, Map.class));
            }
            //2.把数据添加到ES中
            skuEsMapper.saveAll(skuInfoList);
        }

    }

}
