package com.lioch3cooh.glaciersmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
//品牌
public class Brands {
    private Integer id ;
    private String name;
    private String nameEn;
    private String logo;
    private String picture;
    private Integer type;
    private String desc;
    private String place;
}
