package com.lioch3cooh.glaciersmall.service.serviceImpl;

import com.lioch3cooh.glaciersmall.dao.CategoryDao;
import com.lioch3cooh.glaciersmall.dao.GoodsDao;
import com.lioch3cooh.glaciersmall.dao.PropertiesDao;
import com.lioch3cooh.glaciersmall.entity.*;
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

    @Autowired
    private PropertiesDao propertiesDao;

    @Autowired
    private GoodsDao goodsDao;

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
        VoResult voResult = VoResultUnit.getDefaultVoRes();

        Map<String, Object> result = new HashMap<>();
        Category category = categoryDao.getCategoryById(categoryId);
        if (category != null) {
            result.put("id", categoryId);
            result.put("name", category.getName());
            result.put("parentId", category.getParentId());
            List<Brands> brands = new ArrayList<>();
            result.put("brands", brands);

            // 获取 分类ID下的 属性项
            List<Map<String, Object>> saleproperties = new ArrayList<>();
            List<SaleProperties> saleProperties = propertiesDao.listSalePropsByCgyId(categoryId);
            for (SaleProperties saleProperty : saleProperties) {
                Map<String, Object> props = new HashMap<>();
                props.put("id", saleProperty.getId());
                props.put("name", saleProperty.getName());
                List<Properties> properties = propertiesDao.listPropsBySPropsId(saleProperty.getId());
                props.put("properties", properties);
                saleproperties.add(props);
            }
            result.put("saleProperties", saleproperties);
            voResult = VoResultUnit.getSuccessVoRes(voResult, result);
        }

        return voResult;
    }

    @Override
    public VoResult findSubCategoryGoods(FilterData filterData) {

        VoResult voRes = VoResultUnit.getDefaultVoRes();
        List<Goods> goods = null;
        if ( filterData.getCategoryId() != null) {
            List<Attrs> attrs = filterData.getAttrs();
            Integer page = (filterData.getPage() - 1) * filterData.getPageSize();
            Integer size = filterData.getPageSize();
            Integer attrslength = attrs == null ? 0 : attrs.size();
            goods = goodsDao.listGoodsByFilter(attrs, attrslength, filterData.getCategoryId(), page, size);
        }else{
            return voRes;
        }

        Map<String, Object> result = new HashMap<>();
        result.put("items", goods);
        voRes = VoResultUnit.getSuccessVoRes(voRes, result);
        return voRes;
    }
}
