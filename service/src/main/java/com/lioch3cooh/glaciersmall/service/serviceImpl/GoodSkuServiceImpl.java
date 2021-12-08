package com.lioch3cooh.glaciersmall.service.serviceImpl;

import com.lioch3cooh.glaciersmall.dao.CategoryDao;
import com.lioch3cooh.glaciersmall.dao.GoodSkuDao;
import com.lioch3cooh.glaciersmall.dao.GoodsDao;
import com.lioch3cooh.glaciersmall.dao.SpecificationsDao;
import com.lioch3cooh.glaciersmall.entity.Category;
import com.lioch3cooh.glaciersmall.entity.Goods;
import com.lioch3cooh.glaciersmall.entity.Obeans.ProductSkusSpecs;
import com.lioch3cooh.glaciersmall.entity.Obeans.ProductSpecs;
import com.lioch3cooh.glaciersmall.entity.SpecVaule;
import com.lioch3cooh.glaciersmall.entity.Specifications;
import com.lioch3cooh.glaciersmall.entity.vo.VoProduct;
import com.lioch3cooh.glaciersmall.entity.vo.VoSku;
import com.lioch3cooh.glaciersmall.entity.vo.VoSpecs;
import com.lioch3cooh.glaciersmall.service.GoodSkuService;
import com.lioch3cooh.glaciersmall.unity.VoResultUnit;
import com.lioch3cooh.glaciersmall.vo.VoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodSkuServiceImpl implements GoodSkuService {

    @Autowired
    private SpecificationsDao specificationsDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private GoodSkuDao goodSkuDao;

    @Override
    public VoResult getVoProduct(Integer goodId) {
        VoResult defaultVoRes = VoResultUnit.getDefaultVoRes();
        VoProduct voProduct = new VoProduct();
        // 获取物品
        Goods goodById = goodsDao.getGoodById(goodId);
        if (goodById != null) {
            // 获取一个物品的规格名 集合
            List<VoSpecs> specs = getProductSpecs(goodId);
            // 获取分类
            List<Category> categories =
                    listProductCategories(goodById.getCategoryId());
            // 获取Sku
            List<VoSku> skus = listProductSkus(goodId);


            voProduct.setId(goodId);
            voProduct.setName(goodById.getName());
            voProduct.setDesc(goodById.getDesc());
            voProduct.setPrice(goodById.getPrice());
            voProduct.setSpecs(specs);
            voProduct.setCategories(categories);
            voProduct.setSkus(skus);
            List mainPictures = new ArrayList();
            mainPictures.add("http://yjy-xiaotuxian-dev.oss-cn-beijing.aliyuncs.com/picture/2021-04-05/6fdcac19-dd44-442c-9212-f7ec3cf3ed18.jpg");
            mainPictures.add("http://yjy-xiaotuxian-dev.oss-cn-beijing.aliyuncs.com/picture/2021-04-05/45fd1372-05d2-4ff8-8669-79463a1e589f.jpg");
            mainPictures.add("http://yjy-xiaotuxian-dev.oss-cn-beijing.aliyuncs.com/picture/2021-04-05/e5a9f569-accc-4006-9064-2180e7f2b691.jpg");
            mainPictures.add("http://yjy-xiaotuxian-dev.oss-cn-beijing.aliyuncs.com/picture/2021-04-05/81119a14-5311-4011-af4a-273894375c74.jpg");
            voProduct.setMainPictures(mainPictures);

            defaultVoRes = VoResultUnit.getSuccessVoResObject(defaultVoRes, voProduct);
        }

        return defaultVoRes;
    }

    private List<VoSku> listProductSkus(Integer goodId) {
        List<VoSku> voSkus = goodSkuDao.listVoSKU(goodId);

        for (VoSku skus : voSkus) {
            List<ProductSkusSpecs> productSkusSpecs =
                    specificationsDao.listSkuSpecs(skus.getId());
            skus.setSpecs(productSkusSpecs);
        }

        return voSkus;
    }


    // 获取一个物品的规格名
    public List<VoSpecs> getProductSpecs(Integer goodId) {
        List<Specifications> specifications = specificationsDao.listSpecifications(goodId);
        List<VoSpecs> specs = new ArrayList<>();
        VoSpecs voSpecs = null;
        // 如果有数据
        if (specifications.size() > 0) {
            for (Specifications specification : specifications) {
                // 规格名 name values
                voSpecs = new VoSpecs();
                // 创建values
                List<ProductSpecs> values = new ArrayList<>();
                voSpecs.setName(specification.getSpecName());

                // 根据规格ID 获取规格值
                List<SpecVaule> specVaules = specificationsDao.listSpecValue(specification.getSpecId());

                // 规格值 name picture
                ProductSpecs pt = null;
                for (SpecVaule specVaule : specVaules) {
                    pt = new ProductSpecs();
                    pt.setName(specVaule.getSpecValName());
                    pt.setPicture(specVaule.getPicture());
                    values.add(pt);
                }

                voSpecs.setValues(values);
                specs.add(voSpecs);
            }
        }
        return specs;
    }


    // 获取分类
    public List<Category> listProductCategories(Integer categoryId) {
        List<Category> categories = new ArrayList<>();
        Category categoryLevel2 = categoryDao.getCategoryById(categoryId);
        if (categoryLevel2 != null) {
            Category categoryLevel1 = categoryDao.getCategoryByParentId(categoryLevel2.getParentId());
            categories.add(categoryLevel2);
            if (categories != null) {
                categories.add(categoryLevel1);
            }

        }
        return categories;
    }


}
