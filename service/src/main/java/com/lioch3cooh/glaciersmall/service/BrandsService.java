package com.lioch3cooh.glaciersmall.service;

import com.lioch3cooh.glaciersmall.entity.Brands;

import java.util.List;

public interface BrandsService {
    int insertOneBrand(Brands brands);

    // 查询推荐品牌
    List<Brands> listRecommendedBrand(Integer limit);
}
