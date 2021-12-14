package com.lioch3cooh.glaciersmall.entity.RequestBodyEnity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RBEMobileLogin {

    private String mobile;
    private String code;
}
