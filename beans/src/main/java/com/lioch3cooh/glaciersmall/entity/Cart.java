package com.lioch3cooh.glaciersmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 购物车
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart {
    private Integer skuId;
    private Integer selected;
    private Double price;
    private Integer count;
    private String attrsText;
    private String memberId;
}
