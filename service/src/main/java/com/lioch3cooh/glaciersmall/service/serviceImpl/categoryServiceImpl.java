package com.lioch3cooh.glaciersmall.service.serviceImpl;

import com.lioch3cooh.glaciersmall.dao.CategoryDao;
import com.lioch3cooh.glaciersmall.entity.Category;
import com.lioch3cooh.glaciersmall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class categoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> listTopCategory() {
        return categoryDao.listTopCategory();

    }

    @Override
    public Category getCategoryById(Integer id) {
        Category category = categoryDao.getCategoryById(id);
        return category;
    }

    @Override
    public List<Category> getTwoLevelCategory() {
        return categoryDao.getTwoCategory();

    }

    @Override
    public List<Category> listCategoryByParentId(Integer parentId) {
        return categoryDao.listCategoryByParentId(parentId);
    }
}
