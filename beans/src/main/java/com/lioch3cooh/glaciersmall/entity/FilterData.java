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
public class FilterData {

    private String brandId;
    private Integer categoryId;
    private boolean inventory;
    private boolean onlyDiscount;
    private Integer page;
    private Integer pageSize;
    private String sortField;
    private String sortMetho;
    private List<Attrs> attrs;

}
