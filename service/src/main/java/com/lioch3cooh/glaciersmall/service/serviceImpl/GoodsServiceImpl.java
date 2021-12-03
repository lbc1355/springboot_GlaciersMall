package com.lioch3cooh.glaciersmall.service.serviceImpl;

import com.lioch3cooh.glaciersmall.dao.GoodsDao;
import com.lioch3cooh.glaciersmall.entity.Category;
import com.lioch3cooh.glaciersmall.entity.Goods;
import com.lioch3cooh.glaciersmall.service.CategoryService;
import com.lioch3cooh.glaciersmall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;


    @Override
    public int insertOneGood(Goods goods) {
        int i = goodsDao.insertOneGood(goods);
        return i;
    }

    @Override
    public List<Goods> listGoodByCgyId(Integer CategoryId) {
        List<Goods> goodByCgyId = goodsDao.listGoodByCgyId(CategoryId);
        return goodByCgyId;
    }

    @Override
    public List<Goods> listColumnGoods() {
        List<Goods> goods = goodsDao.listColumnGoods();
        return null;
    }

    @Override
    public List<Goods> listColumnGoodsByCgyId(Integer categoryId) {
        List<Goods> goods = goodsDao.listColumnGoodsByCgyId(categoryId);
        return goods;
    }


}
