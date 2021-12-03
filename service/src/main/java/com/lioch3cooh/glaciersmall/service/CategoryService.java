package com.lioch3cooh.glaciersmall.service;

import com.lioch3cooh.glaciersmall.entity.Category;


import java.util.List;

public interface CategoryService {
    /**
     * 获取 八大分类
     */
    List<Category> listTopCategory();

    /**
     * 根据ID 获取分类
     * @param id
     * @return
     */
    Category getCategoryById(Integer id);

    /**
     * 获取 二级分类
     */
    List<Category> getTwoLevelCategory();

    /**
     * 根据上级id查询分类
     */
    List<Category> listCategoryByParentId(Integer parentId);
}
