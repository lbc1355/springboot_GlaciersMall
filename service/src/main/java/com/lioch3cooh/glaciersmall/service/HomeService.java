package com.lioch3cooh.glaciersmall.service;

import com.lioch3cooh.glaciersmall.entity.*;


import java.util.List;

public interface HomeService {
    /**
     *首页获取推荐品牌 获取顶级分类
     */
    List<Category> listTopCategory();

    /**
     *首页获取推荐品牌 根据ID获取父级分类
     */
    List<Category> listCategoryByParentId(Integer parentId);

    /**
     *首页获取推荐品牌 : 根据分类ID获取推荐物品
     */
    List<Goods> getRecomGoodByCgyId(Integer categoryID);


    /**
     *获取新鲜好物
     */

    List<Goods> listNewGoods();

    /**
     *获取人气推荐
     */
    List<Hot> listHomeHot();

    /**
     * 获首页最新专题
     * @return
     */
    List<Special> listHomeSpecial();

    /**
     * 首页获取产品专区的分类
     * @return
     */
    List<VoHomeProductCategory> listProductCategory();

    /**
     * 首页获取产品专区的物品
     * @return
     */
    List<Goods> listProductGoods();
}
