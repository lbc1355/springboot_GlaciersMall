package com.lioch3cooh.glaciersmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * 首页产品区块--分类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VoHomeProductCategory {
    private Integer id;
    private String name;
    private String picture;
    private String saleInfo;
    private List children;
    private List<Goods> goods;
}
