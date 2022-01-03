package com.changgou.goods.feign;

import com.changgou.goods.pojo.Sku;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author ljh
 * @version 1.0
 * @date 2020/9/26 14:49
 * @description 标题
 * @package com.changgou.goods.feign
 */
@FeignClient(name="goods",path = "/sku")
// path = "/sku" 和 @requestMaping("/sku")效果一样
public interface SkuFeign {
    @GetMapping("/status/{status}")
    public Result<List<Sku>> findByStatus(@PathVariable(name="status") String status);
}
