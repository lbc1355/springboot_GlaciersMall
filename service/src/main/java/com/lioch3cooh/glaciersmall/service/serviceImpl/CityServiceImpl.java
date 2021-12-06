package com.lioch3cooh.glaciersmall.service.serviceImpl;

import com.lioch3cooh.glaciersmall.dao.CityDao;
import com.lioch3cooh.glaciersmall.entity.Region;
import com.lioch3cooh.glaciersmall.service.CityService;
import com.lioch3cooh.glaciersmall.vo.VoCity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CityServiceImpl implements CityService {

    @Autowired
    private CityDao cityDao;

    @Override
    public int insertOneRegion(Region city) {
        return cityDao.insertOneRegion(city);
    }

    @Override
    public Region getOneRegion(Integer code) {
        return cityDao.getOneRegion(code);
    }

    @Override
    public List<Region> listRegionsByParId(Integer parentId) {
        return cityDao.listRegionsByParId(parentId);
    }

    @Override
    public List<VoCity<Region>> listAllCity() {
        // 获取省
        List<Region> provinces = cityDao.listProvince();

        // 获取市
        List<Region> cities = cityDao.listCities();

        // 获取县
        List<Region> county = cityDao.listCounty();

        return null;
    }


}
