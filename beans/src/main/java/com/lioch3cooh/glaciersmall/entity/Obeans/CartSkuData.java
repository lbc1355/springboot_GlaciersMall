package com.lioch3cooh.glaciersmall.entity.Obeans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 返回给前端
 * 用户购物车SKU信息的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartSkuData {

    private String attrsText;
    private Integer count;
    private Double discount;
    private Integer id;
    private Boolean isCollect;
    private Boolean isEffective;
    private String name;
    private Double nowOriginalPrice;
    private Double nowPrice;
    private String picture;
    private Integer postFee;
    private Double price;
    private Boolean selected;
    private Integer skuId;
    private List specs;
    private Integer stock;

}

