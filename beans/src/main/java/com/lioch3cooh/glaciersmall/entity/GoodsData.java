package com.lioch3cooh.glaciersmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GoodsData {

    private String brandId;
    private String categoryId;
    private boolean inventory;
    private boolean onlyDiscount;
    private Integer page;
    private Integer pageSize;
    private String sortField;
    private String sortMetho;
    private List<Attrs> attrs;

}
