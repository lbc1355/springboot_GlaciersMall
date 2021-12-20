package com.lioch3cooh.glaciersmall.dao;

import com.lioch3cooh.glaciersmall.entity.UserAddresses;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AddressDao {

    /**
     * 寻找一个用户的全部地址
     * @param memberId
     * @return
     */
    List<UserAddresses> listAddress(@Param("memberId") String memberId);

    int insertAddress(@Param("userAddresses") UserAddresses userAddresses);

    int updateAddress(@Param("addresses") UserAddresses addresses);

    UserAddresses queryAddrById(@Param("addressId") int addressId);
}
