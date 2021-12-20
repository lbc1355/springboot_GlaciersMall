package com.lioch3cooh.glaciersmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Sku {
    private Integer id;
    private String name;
    private Integer goodId;
    private Double price;
    private Double oldPrice;
    private Integer inventory;
    private Integer isEffective;
    private Double discount;
}
