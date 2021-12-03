package com.lioch3cooh.glaciersmall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VoCategory {
    private Integer id;
    private String name;
    private String picture;
    private List children;
    private List goods;
}
