package com.lioch3cooh.glaciersmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Special {
    private String id;
    private String creator;
    private int isDelete;
    private int classificationId;
    private String title;
    private String summary;
    private String lowestPrice;
    private String cover;
    private String detailsUrl;
    private int viewNum;
    private int collectNum;
    private int replyNum;
    private String createTime;
    private String updateTime;
}
