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
     * 根据上级id查询分类
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
