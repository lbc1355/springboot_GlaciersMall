package com.lioch3cooh.glaciersmall;

import com.lioch3cooh.glaciersmall.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategorySP {
    private String id;
    private String name;
    private String picture;
    private String parentId;
    private String parentName;
    private List<Goods> goods;
    private String categories;
    private String brands;
    private String saleProperties;
}
