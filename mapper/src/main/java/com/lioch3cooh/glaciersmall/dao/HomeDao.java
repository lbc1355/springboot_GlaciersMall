package com.lioch3cooh.glaciersmall.dao;

import com.lioch3cooh.glaciersmall.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 这首页界面中获取的数据
 */
@Mapper
public interface HomeDao {
    /**
     * 首页获取推荐品牌 : 获取顶级分类
     *
     * @return
     */

    List<Category> listTopCategory();

    /**
     * 首页获取推荐品牌 : 根据ID获取父级分类
     *
     * @param parentId
     * @return
     */
    List<Category> listCategoryByParentID(@Param("parentId") Integer parentId);

    /**
     * 首页获取推荐品牌 : 根据分类ID获取推荐物品
     */
    List<Goods> getGoodByCgyId(@Param("categoryId") Integer categoryID);

    /**
     * 首页获取新鲜好物
     */
    List<Goods> listNewGoods();

    /**
     * 首页人气推荐
     *
     * @return
     */
    List<Hot> listHomeHot();

    /**
     * 获首页最新专题
     *
     * @return
     */
    List<Special> listHomeSpecial();

    /**
     * 获取产品区块内容
     * 分类
     * @return
     */
    List<VoHomeProductCategory> listProductCategory();

    /**
     * 获取产品区块内容
     * 物品
     * @return
     */
    List<Goods> listProductGoods();
}
