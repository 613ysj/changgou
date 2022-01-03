package com.changgou.search.service;

import java.util.Map;

/**
 * @author: saijie
 * @date: 2022/1/2 20:44
 */
public interface SkuService {
    void importSku();

    Map search(Map<String, String> searchMap);
}
