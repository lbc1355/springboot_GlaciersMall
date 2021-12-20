package com.lioch3cooh.glaciersmall.dao;

import com.lioch3cooh.glaciersmall.entity.AddressSnapshot;
import com.lioch3cooh.glaciersmall.entity.Order;
import com.lioch3cooh.glaciersmall.entity.SkuSnaoshot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
    /**
     * 插入一个订单
     *
     * @param order
     */
    void insertOrder(@Param("order") Order order);

    int insertAddressSnap(@Param("snapshot") AddressSnapshot snapshot);

    void insertSkuSnap(@Param("snapshot") SkuSnaoshot snaoshot);

    Order getOrder(@Param("orderId") String orderId);

    void setOrderTimeout(@Param("orderId") String orderId);

    void updateOrderStatus(@Param("status")int status, @Param("orderId") String orderId);
}
