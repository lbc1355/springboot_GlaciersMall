package com.lioch3cooh.glaciersmall.entity.vo;

import com.lioch3cooh.glaciersmall.entity.Obeans.ProductSkusSpecs;
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
public class VoSku {
    private Integer id;
    private String name;
    private Double price;
    private Double oldPrice;
    private Integer inventory;
    List<ProductSkusSpecs> specs;
}
