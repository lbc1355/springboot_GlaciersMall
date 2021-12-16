package com.lioch3cooh.glaciersmall.service;

import com.lioch3cooh.glaciersmall.entity.RequestBodyEnity.MergeCart;
import com.lioch3cooh.glaciersmall.vo.VoResult;

import java.util.List;
import java.util.Map;

public interface CartService {
    /**
     * 将前端传来的商品 合并到数据库
     *
     * @param mergeCartList
     * @param memberId
     */
    VoResult mergeCart(List<MergeCart> mergeCartList, String memberId);

    /**
     * 获取用户购物车信息
     * @param memberId
     * @return
     */
    VoResult findCart(String memberId);

    VoResult deleteCart(Map<String, List<Integer>> ids, String memberId);

    VoResult insertCart(Map<String , Integer> ids, String memberId);

    VoResult updateCart(Object count, Object selected, Integer skuId, String memberId);

    VoResult checkAllCart(Object selected, Object ids, String memberId);
}
