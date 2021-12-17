package com.lioch3cooh.glaciersmall.dao;

import com.lioch3cooh.glaciersmall.entity.UserAddresses;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AddressDao {


    List<UserAddresses> listAddress(@Param("memberId") String memberId);

    int insertAddress(@Param("userAddresses") UserAddresses userAddresses);

    int updateAddress(@Param("addresses") UserAddresses addresses);
}
