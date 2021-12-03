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
public class VoResult {
    private int code;
    private String msg;
    private Object result;
}
