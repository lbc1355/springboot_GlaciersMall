package com.lioch3cooh.glaciersmall.service;

import com.lioch3cooh.glaciersmall.vo.VoResult;

public interface OrderService {
    /**
     * 填写订单
     * @param memberId
     * @return
     */
    VoResult createOrder(String memberId);
}
