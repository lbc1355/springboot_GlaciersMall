package com.lioch3cooh.glaciersmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order implements Serializable {

    public static final Integer ORDER_NOTPAY = 1;
    public static final Integer ORDER_TIMEOUT = 2;
    public static final Integer ORDER_SUCCESS = 3;
    public static final Integer ORDER_DROPSHIPP = 4;


    //单订ID
    private String id;
    // 用户ID
    private String memberId;
    // 付支方式
    private Integer payType;
    // 订单状态
    private Integer orderState;
    // 费邮
    private Double postFee;
    // 实付金额
    private Double payMoney;
    //	金额合计
    private Double totalMoney;
    // 	数量合计
    private Integer totalNum;
    //	建创订单时间
    private String createTime;
    // 	付款时间
    private String payTime;

    private Integer deliveryTimeTypel;

    private Integer payChannel;

    private String buyerMessage;


}
