package com.lioch3cooh.glaciersmall.service;

import com.lioch3cooh.glaciersmall.vo.VoResult;

import java.util.Map;

public interface OrderService {
    /**
     * 订单结算信息
     * @param memberId
     * @return
     */
    VoResult createOrder(String memberId);

    VoResult submitOrder(Map payload, String memberId);

    VoResult getOrderStatu(String orderId, String memberId);

    void setOrderOverdue(String orderId);

    void setOrderStatus(int i, String orderId);
}
