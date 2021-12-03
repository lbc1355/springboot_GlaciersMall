package com.lioch3cooh.glaciersmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
// 轮播图
public class Banner {
    private Integer id;
    private String imgUrl;
    private String hrefUrl;
    private Integer type;

}
