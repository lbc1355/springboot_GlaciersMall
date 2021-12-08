package com.lioch3cooh.glaciersmall.dao;

import com.lioch3cooh.glaciersmall.entity.SpecVaule;
import com.lioch3cooh.glaciersmall.entity.Specifications;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SpecificationsDao {
    /**
     * 根据物品ID获取规格名称
     * @param goodId
     * @return
     */
    List<Specifications> listSpecifications(@Param("goodId") Integer goodId);

    /**
     * 根据规格ID 获取规格值
     */

    List<SpecVaule> listSpecValue(@Param("specId") Integer specId);
}
