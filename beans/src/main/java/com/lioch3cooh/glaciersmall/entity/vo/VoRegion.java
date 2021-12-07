package com.lioch3cooh.glaciersmall.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VoRegion<T> {
    private Integer code;
    private Integer level;
    private String name;
    private List<T> areaList;
}
