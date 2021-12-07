package com.lioch3cooh.glaciersmall.dao;

import com.lioch3cooh.glaciersmall.entity.Region;
import com.lioch3cooh.glaciersmall.entity.vo.VoRegion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RegionDao {

    int insertOneRegion(@Param("region") Region region);

    Region getOneRegion(@Param("code") Integer code);

    List<Region> listProvince();

    List<Region> listCities();

    List<Region> listCounty();

    List<Region> listRegionsByParId(@Param("parentId") Integer parentId);

    /**
     * 获取Vo 省
     * @return
     */
    List<VoRegion<Region>> listVoRegionProvince();

    /**
     * 获取Vo地区 通过父ID
     * @param parentId
     * @return
     */
    List<VoRegion<Region>> listVoRegionsByParId(@Param("parentId") Integer parentId);



}
