package com.lioch3cooh.glaciersmall.dao;

import com.lioch3cooh.glaciersmall.entity.Banner;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface BannerDao {
    // 获取轮播图内容
    List<Banner> getBannerContext();
}
