package com.lioch3cooh.glaciersmall.entity.RequestBodyEnity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用户登录后
 * 前端将本地购物车中的物品上传上来的实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MergeCart {
    private Integer count;
    private Boolean selected;
    private Integer skuId;
}
