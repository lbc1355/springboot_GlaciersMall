package com.lioch3cooh.glaciersmall.entity.vo;

import com.lioch3cooh.glaciersmall.entity.Category;
import com.lioch3cooh.glaciersmall.entity.Specifications;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VoProduct {
    private Integer id;
    private String name;
    private String desc;
    private double price;
    private double oldPrice;
    private List<String> mainPictures;
    private List<VoSpecs> specs;
    private List<VoSku> skus;


    private List<Category> categories;
    private List details;
    private List similarProducts;
    private List hotByDay;

}
