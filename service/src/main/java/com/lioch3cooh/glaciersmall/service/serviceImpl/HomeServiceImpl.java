package com.lioch3cooh.glaciersmall.service.serviceImpl;

import com.lioch3cooh.glaciersmall.dao.HomeDao;
import com.lioch3cooh.glaciersmall.entity.*;
import com.lioch3cooh.glaciersmall.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private HomeDao homeDao;

    @Override
    public List<Category> listTopCategory() {
        return homeDao.listTopCategory();
    }

    @Override
    public List<Category> listCategoryByParentId(Integer parentId) {
        return homeDao.listCategoryByParentID(parentId);
    }

    @Override
    public List<Goods> getRecomGoodByCgyId(Integer categoryID) {
        return homeDao.getGoodByCgyId(categoryID);
    }

    @Override
    public List<Goods> listNewGoods() {
        List<Goods> goods = homeDao.listNewGoods();
        return goods;
    }

    @Override
    public List<Hot> listHomeHot() {
        return homeDao.listHomeHot();
    }

    @Override
    public List<Special> listHomeSpecial() {
        return homeDao.listHomeSpecial();
    }

    @Override
    public List<VoHomeProductCategory> listProductCategory() {
        List<VoHomeProductCategory> voHomeProductCategories = homeDao.listProductCategory();
        return voHomeProductCategories;
    }

    @Override
    public List<Goods> listProductGoods() {
        List<Goods> goods = homeDao.listProductGoods();
        return goods;
    }


}
