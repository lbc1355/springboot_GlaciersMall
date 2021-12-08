package com.lioch3cooh.glaciersmall.entity.vo;

import com.lioch3cooh.glaciersmall.entity.SpecVaule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VoSpecs {
    private String name;
    private List<SpecVaule> values;
}
