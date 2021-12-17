package com.lioch3cooh.glaciersmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserAddresses {

    private Integer id;
    private String address;
    private String addressTags;
    private String cityCode;
    private String contact;
    private String countyCode;
    private String fullLocation;
    private Integer isDefault;
    private String postalCode;
    private String provinceCode;
    private String receiver;

}
