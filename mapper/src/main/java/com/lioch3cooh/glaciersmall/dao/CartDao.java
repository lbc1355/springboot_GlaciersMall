package com.lioch3cooh.glaciersmall.dao;

import com.lioch3cooh.glaciersmall.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CartDao {
    /**
     * 查询用户的购物车
     *
     * @return
     */
    List<Cart> listCart(@Param("memberId") String memberId);

    /**
     * 查询某个用户的购物车中的某个SKU
     *
     * @param memberId
     * @param skuId
     * @return
     */
    Cart queryCart(@Param("memberId") String memberId, @Param("skuId") Integer skuId);

    /**
     * 加入一个商品到购物车
     *
     * @param cart
     * @return
     */
    int insertCart(@Param("cart") Cart cart);

    int updateCart(@Param("cart") Cart cart);

    void deleteCart(@Param("skuIds") Map<String, List<Integer>> ids,
                    @Param("memberId") String memberId);
}
