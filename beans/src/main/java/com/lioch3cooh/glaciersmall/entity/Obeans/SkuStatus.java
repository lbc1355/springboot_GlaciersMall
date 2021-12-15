package com.lioch3cooh.glaciersmall.entity.Obeans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用于 查询SKU状态 返回的实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SkuStatus {

    private double nowPrice;
    private double oldPrice;
    private Integer stock;
    private double discount;
    private Integer isEffective;

}
