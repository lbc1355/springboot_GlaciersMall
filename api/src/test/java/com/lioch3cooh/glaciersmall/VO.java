package com.lioch3cooh.glaciersmall;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VO {
    private int code;
    private String msg;
    private Item result;
}
