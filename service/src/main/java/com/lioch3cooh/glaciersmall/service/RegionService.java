package com.lioch3cooh.glaciersmall.service;

import com.lioch3cooh.glaciersmall.entity.Region;
import com.lioch3cooh.glaciersmall.entity.vo.VoRegion;

import java.util.List;

public interface RegionService {
    int insertOneRegion(Region city);

    Region getOneRegion(Integer code);

    List<Region> listRegionsByParId(Integer parentId);
    /**
     * 获取全部 省市县
     * @return
     */
    List<VoRegion<Region>> listAllCity();

}
