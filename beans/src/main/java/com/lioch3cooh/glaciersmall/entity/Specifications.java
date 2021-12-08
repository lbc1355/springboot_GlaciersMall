package com.lioch3cooh.glaciersmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Specifications {
    private Integer specId;
    private String specName;
    private Integer goodId;
}
