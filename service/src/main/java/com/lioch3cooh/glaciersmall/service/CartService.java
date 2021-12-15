package com.lioch3cooh.glaciersmall.service;

import com.lioch3cooh.glaciersmall.entity.RequestBodyEnity.MergeCart;
import com.lioch3cooh.glaciersmall.vo.VoResult;

import java.util.List;

public interface CartService {
    /**
     * 将前端传来的商品 合并到数据库
     *
     * @param mergeCartList
     * @param memberId
     */
    VoResult mergeCart(List<MergeCart> mergeCartList, String memberId);
}
