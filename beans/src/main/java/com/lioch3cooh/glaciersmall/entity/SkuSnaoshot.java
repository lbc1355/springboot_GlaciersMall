package com.lioch3cooh.glaciersmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SkuSnaoshot {
    private Integer id;
    private Integer goodId;
    private Integer skuId;
    private String name;
    private Double price;
    private Integer count;
    private String picture;
    private String orderId;
    private String specsText;
}
