package com.lioch3cooh.glaciersmall.service.serviceImpl;

import com.lioch3cooh.glaciersmall.dao.BrandsDao;
import com.lioch3cooh.glaciersmall.entity.Brands;
import com.lioch3cooh.glaciersmall.service.BrandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandsServiceImpl implements BrandsService {

    @Autowired
    private BrandsDao brandsDao;

    //  添加一个品牌
    @Override
    public int insertOneBrand(Brands brands) {
        return brandsDao.insertOneBrand(brands);
    }

    @Override
    public List<Brands> listRecommendedBrand(Integer limit) {
        return brandsDao.listRecommendedBrand(limit);
    }
}
