package com.lioch3cooh.glaciersmall.dao;

import com.lioch3cooh.glaciersmall.entity.Category;
import com.lioch3cooh.glaciersmall.entity.Properties;
import com.lioch3cooh.glaciersmall.entity.SaleProperties;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryDao {
    /**
     * 获取顶级分类
     *
     * @return
     */
    List<Category> listTopCategory();


    /**
     * 根据id返回分类
     *
     * @param id
     * @return category
     */
    Category getCategoryById(@Param("id") Integer id);

    /**
     * 获取二级分类
     *
     * @return
     */
    List<Category> getTwoCategory();

    /**
     * 查询父ID 的 子分类
     *
     * @param parentId
     * @return
     */
    List<Category> listCategoryByParentId(@Param("parentId") Integer parentId);

    /**
     * 根据name找分类
     *
     * @param name
     * @return
     */
    Category getCategoryByName(@Param("name") String name);


    /**
     * 根据父ID 查找父分类
     * @param parentId
     * @return
     */
    Category getCategoryByParentId(@Param("parentId") Integer parentId);




}
