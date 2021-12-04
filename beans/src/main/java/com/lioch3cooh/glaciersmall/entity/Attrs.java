package com.lioch3cooh.glaciersmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 前端 传来的条件查询对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Attrs {

    private Integer groupId;
    private String groupName;
    private Integer propertiesId;
    private String propertyName;

}
