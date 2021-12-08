package com.lioch3cooh.glaciersmall.service;

import com.lioch3cooh.glaciersmall.entity.vo.VoProduct;
import com.lioch3cooh.glaciersmall.vo.VoResult;

public interface GoodSkuService {
    /**
     * 根据商品ID 获取商品详细信息
     * @return
     */
    VoResult getVoProduct(Integer goodId);
}
