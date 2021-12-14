package com.lioch3cooh.glaciersmall.service.serviceImpl;

import com.lioch3cooh.glaciersmall.dao.BannerDao;
import com.lioch3cooh.glaciersmall.entity.Banner;
import com.lioch3cooh.glaciersmall.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class bannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;


    @Override
    public List<Banner> getBannerContext() {
        List<Banner> bannerContext = bannerDao.getBannerContext();

        return bannerContext;
    }
}
