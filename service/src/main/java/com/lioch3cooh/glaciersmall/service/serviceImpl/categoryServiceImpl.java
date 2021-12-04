package com.lioch3cooh.glaciersmall.service.serviceImpl;

import com.lioch3cooh.glaciersmall.dao.CategoryDao;
import com.lioch3cooh.glaciersmall.entity.Brands;
import com.lioch3cooh.glaciersmall.entity.Category;
import com.lioch3cooh.glaciersmall.entity.Properties;
import com.lioch3cooh.glaciersmall.entity.SaleProperties;
import com.lioch3cooh.glaciersmall.service.CategoryService;
import com.lioch3cooh.glaciersmall.unity.VoResultUnit;
import com.lioch3cooh.glaciersmall.vo.VoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public VoResult findSubCategoryFilter(Integer categoryId) {
        VoResult defaultVoRes = VoResultUnit.getDefaultVoRes();

        Map<String, Object> result = new HashMap<>();
        Category category = categoryDao.getCategoryById(categoryId);
        result.put("id", categoryId);
        result.put("name", category.getName());
        result.put("parentId", category.getParentId());
        List<Brands> brands = new ArrayList<>();
        result.put("brands", brands);

        // 获取 分类ID下的 属性项
        List<Map<String, Object>> saleproperties = new ArrayList<>();
        List<SaleProperties> saleProperties = categoryDao.listSalePropsByCgyId(categoryId);
        for (SaleProperties saleProperty : saleProperties) {
            Map<String, Object> props = new HashMap<>();
            props.put("id", saleProperty.getId());
            props.put("name", saleProperty.getName());
            List<Properties> properties = categoryDao.listPropsBySPropsId(saleProperty.getId());
            props.put("properties", properties);
            saleproperties.add(props);
        }
        result.put("saleProperties", saleproperties);
        VoResult successVo = VoResultUnit.getSuccessVoRes(defaultVoRes, result);
        return successVo;
    }
}
