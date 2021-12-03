package com.lioch3cooh.glaciersmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
    private Integer id;
    private String name;
    private String picture;
    private Integer level;
    private Integer parent_id;
//    private List<Category> children;
//    private List<Goods> goods;
}
