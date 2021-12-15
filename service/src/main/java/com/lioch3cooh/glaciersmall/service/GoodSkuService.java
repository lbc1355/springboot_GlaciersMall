package com.lioch3cooh.glaciersmall.service;

import com.lioch3cooh.glaciersmall.entity.Obeans.SkuStatus;
import com.lioch3cooh.glaciersmall.entity.vo.VoProduct;
import com.lioch3cooh.glaciersmall.vo.VoResult;

import java.util.Map;

public interface GoodSkuService {
    /**
     * 根据商品ID 获取商品详细信息
     * @return
     */
    VoResult getVoProduct(Integer goodId);


    /**
     * 查询一个SKU 的状态 ("nowPrice","oldPrice","stock","discount","isEffective" )
     * @param skuId
     */
    VoResult getSkuStatus(Integer skuId);


    /**
     * 获取商品对应的sku数据
     */
    VoResult getGoodsSku(Integer skuId);

}
