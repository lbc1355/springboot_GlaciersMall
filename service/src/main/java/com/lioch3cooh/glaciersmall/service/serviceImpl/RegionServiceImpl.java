package com.lioch3cooh.glaciersmall.service.serviceImpl;

import com.lioch3cooh.glaciersmall.dao.RegionDao;
import com.lioch3cooh.glaciersmall.entity.Region;
import com.lioch3cooh.glaciersmall.service.RegionService;
import com.lioch3cooh.glaciersmall.entity.vo.VoRegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionDao regionDao;

    @Override
    public int insertOneRegion(Region city) {
        return regionDao.insertOneRegion(city);
    }

    @Override
    public Region getOneRegion(Integer code) {
        return regionDao.getOneRegion(code);
    }

    @Override
    public List<Region> listRegionsByParId(Integer parentId) {
        return regionDao.listRegionsByParId(parentId);
    }

    @Override
    public List<VoRegion<Region>> listAllCity() {

        List<VoRegion<Region>> voRegions = regionDao.listVoRegionProvince();
        for (VoRegion voRegion : voRegions) {
            List<VoRegion<Region>> voCities = regionDao.listVoRegionsByParId(voRegion.getCode());
            voRegion.setAreaList(voCities);
            for (VoRegion vocity : voCities) {
                List<VoRegion<Region>> voCounties = regionDao.listVoRegionsByParId(vocity.getCode());
                vocity.setAreaList(voCounties);
            }
        }

        return voRegions;
    }


}
