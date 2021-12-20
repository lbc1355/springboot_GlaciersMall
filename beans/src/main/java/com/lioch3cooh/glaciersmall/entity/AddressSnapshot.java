package com.lioch3cooh.glaciersmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressSnapshot {

    private Integer id;
    private Integer adressId;
    private String receiver;
    private String contact;
    private String fullLocation;
    private String address;
    private String orderId;


}
