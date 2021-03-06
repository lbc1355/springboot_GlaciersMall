package com.lioch3cooh.glaciersmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Members {

    private String id;
    private String account;
    private String password;
    private String nickname;
    private Integer creator;
    private Integer isDelete;
    private String createTime;
    private String updateTime;
    private String mobile;
    private String unionId;
    private String source;
    private String avatar;
    private String gender;

}
