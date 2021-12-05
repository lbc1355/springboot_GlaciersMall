package com.lioch3cooh.glaciersmall.dao;

import com.lioch3cooh.glaciersmall.entity.Properties;
import com.lioch3cooh.glaciersmall.entity.SaleProperties;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PropertiesDao{
    /**
     * 根据分类ID 找属性选项名
     *
     * @param categoryId
     * @return
     */
    List<SaleProperties> listSalePropsByCgyId(@Param("categoryId") Integer categoryId);

    /**
     * 根据属性名ID 找属性值
     *
     * @param salePropId
     * @return
     */
    List<Properties> listPropsBySPropsId(@Param("salePropId") Integer salePropId);



}
