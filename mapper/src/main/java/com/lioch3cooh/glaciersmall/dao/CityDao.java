package com.lioch3cooh.glaciersmall.dao;

import com.lioch3cooh.glaciersmall.entity.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CityDao {
    int insertOneRegion(@Param("region") Region region);

    Region getOneRegion(@Param("code") Integer code);

    List<Region> listProvince();

    List<Region> listCities();

    List<Region> listCounty();

    List<Region> listRegionsByParId(@Param("parentId") Integer parentId);
}
