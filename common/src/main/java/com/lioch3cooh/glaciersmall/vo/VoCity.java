package com.lioch3cooh.glaciersmall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VoCity<T> {
    private Integer code;
    private Integer level;
    private String name;
    private List<T> areaList;
}
