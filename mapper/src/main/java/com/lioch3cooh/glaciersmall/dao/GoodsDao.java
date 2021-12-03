package com.lioch3cooh.glaciersmall.dao;

import com.lioch3cooh.glaciersmall.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsDao {

    int insertOneGood(Goods goods);

    /**
     *根据分类id后获取物品
     */
    List<Goods> listGoodByCgyId(@Param("categoryId") Integer CategoryId);

    /**
     *  根据name获取物品
     */
    Goods getGoodByName(@Param("name")String name);

    /**
     * 修改物品
     */
    int updateGood(@Param("good") Goods goods);

    /**
     * category?id=
     * 专栏-获取全部推荐商品
     */
    List<Goods> listColumnGoods();

    /**
     * 专栏-根据分类ID获取推荐商品
     */
    List<Goods> listColumnGoodsByCgyId(@Param("categoryid") Integer categoryid);

    /**
     * 专栏-插入推荐商品
     */
    int insertGoodToColumn(@Param("id") Integer id);
}
