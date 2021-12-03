package com.lioch3cooh.glaciersmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Goods {
    private Integer id;
    private String name;
    private String desc;
    private String price;
    private String picture;
    private String discount;
    private int orderNum;
    private int categoryId;
}
