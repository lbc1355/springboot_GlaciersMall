package com.lioch3cooh.glaciersmall.dao;

import com.lioch3cooh.glaciersmall.entity.Attrs;
import com.lioch3cooh.glaciersmall.entity.Goods;
import com.lioch3cooh.glaciersmall.entity.SaleProperties;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsDao {

    int insertOneGood(Goods goods);

    /**
     * 根据分类id后获取物品
     */
    List<Goods> listGoodByCgyId(@Param("categoryId") Integer CategoryId);

    /**
     * 根据name获取物品
     */
    Goods getGoodByName(@Param("name") String name);

    Goods getGoodById(@Param("id") Integer id);

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

    /**
     * 根据筛选条件 获取商品集合
     * @param attrs  条件集合
     * @param attrsLength 条件长度
     * @param categoryId 分类ID
     * @param page 页数
     * @param size 页大小
     * @return
     */
    List<Goods> listGoodsByFilter(@Param("attrs") List<Attrs> attrs, Integer attrsLength,
                                  Integer categoryId,Integer page,Integer size);


}
