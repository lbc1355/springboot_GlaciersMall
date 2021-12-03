package com.lioch3cooh.glaciersmall.dao;

import com.lioch3cooh.glaciersmall.entity.Brands;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BrandsDao{
    // 添加一个品牌
    int insertOneBrand(Brands brands);

    // 查询推荐品牌
    List<Brands> listRecommendedBrand(@Param("limit") Integer limit);
}
