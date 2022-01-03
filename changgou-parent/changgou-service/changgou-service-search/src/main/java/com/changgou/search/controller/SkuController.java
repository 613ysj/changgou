package com.changgou.search.controller;

import com.changgou.search.service.SkuService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: saijie
 * @date: 2022/1/2 20:39
 */
@RestController
@RequestMapping("/search")
public class SkuController {

    @Autowired
    private SkuService skuService;
    /**
     * 查询所有的商品的数据导入到es中
     * @return
     */
    @GetMapping("/import")
    public Result importFromDbToEs(){
        skuService.importSku();

        //3.返回/true/false
        return new Result(true, StatusCode.OK,"导入成功");
    }
}
