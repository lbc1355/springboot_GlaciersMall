package com.lioch3cooh.glaciersmall.service;


import com.lioch3cooh.glaciersmall.entity.Goods;

import java.util.List;

public interface GoodsService {
    /**
     * 插入一个商品
     *
     * @param goods
     * @return
     */
    int insertOneGood(Goods goods);

    /**
     * 根据分类id后获取物品
     */
    List<Goods> listGoodByCgyId(Integer CategoryId);

    /**
     * 获取专栏的全部推荐物品
     *
     * @return
     */
    List<Goods> listColumnGoods();


    /**
     * 根据分类ID后去专栏推荐商品
     */
    List<Goods> listColumnGoodsByCgyId(Integer categoryId);


}
