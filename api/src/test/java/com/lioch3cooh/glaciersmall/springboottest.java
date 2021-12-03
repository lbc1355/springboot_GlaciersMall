package com.lioch3cooh.glaciersmall;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kevinsawicki.http.HttpRequest;
import com.lioch3cooh.glaciersmall.dao.CategoryDao;
import com.lioch3cooh.glaciersmall.dao.GoodsDao;
import com.lioch3cooh.glaciersmall.entity.Goods;
import com.lioch3cooh.glaciersmall.service.BrandsService;
import com.lioch3cooh.glaciersmall.service.HomeService;
import com.lioch3cooh.glaciersmall.service.SpecialService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@SpringBootTest
public class springboottest {

    @Autowired
    GoodsDao goodsDao;
    @Autowired
    private BrandsService brandsService;
    @Autowired
    private SpecialService specialService;
    @Autowired
    private HomeService homeService;
    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void httpTest() {
//        String response = HttpRequest.get("http://pcapi-xiaotuxian-front.itheima.net/home/category/head").body();
//
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            VoResult voResult = mapper.readValue(response, VoResult.class);
//            List result = voResult.getResult();
//            String s = mapper.writeValueAsString(result);
//            List<Category> categories = mapper.readValue(s, new TypeReference<List<Category>>() {});
//            for (Category category : categories) {
//                List<Goods> goods = category.getGoods();
//                for (Goods good : goods) {
//                    goodsDao.insertOneGood(good);
//                }
//            }
//
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

    }

//    @Test
//    public void getBrands() {
//        String response = HttpRequest.get("http://pcapi-xiaotuxian-front.itheima.net/home/brand?limit=10").body();
//
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            VoResult voResult = mapper.readValue(response, VoResult.class);
//            List result = voResult.getResult();
//            String s = mapper.writeValueAsString(result);
//            List<Brands> brands = mapper.readValue(s, new TypeReference<List<Brands>>() {
//            });
//            for (Brands brand : brands) {
//                int i = brandsService.insertOneBrand(brand);
//                System.out.println("添加成功");
//            }
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }


//    @Test
//    public void updategoods() {
//        String response = HttpRequest.get("http://pcapi-xiaotuxian-front.itheima.net/category?id=19999999").body();
//        ObjectMapper mapper = new ObjectMapper();
//        VO voResult = null;
//        try {
//            voResult = mapper.readValue(response, VO.class);
//            VoCategoryGoods result = voResult.getResult();
//            List<Item> children = result.getChildren();
//            for (Item child : children) {
//                String name = child.getName();
//                Category categoryByName = categoryDao.getCategoryByName(name);
//                // 获取分类ID
//                Integer id = categoryByName.getId();
//
//                List<com.lioch3cooh.glaciersmall.vo.Goods> goods = child.getGoods();
//                for (com.lioch3cooh.glaciersmall.vo.Goods good : goods) {
//                    System.out.println("======");
//                    System.out.println(good);
//                    Goods goodByName = goodsDao.getGoodByName(good.getName());
//                    if (goodByName == null) {
//                        Goods newgood = new Goods();
//                        newgood.setDesc(good.getDesc());
//                        newgood.setCategoryId(id);
//                        newgood.setDiscount(good.getDiscount());
//                        newgood.setName(good.getName());
//                        newgood.setPrice(good.getPrice());
//                        newgood.setPicture(good.getPicture());
//                        newgood.setOrderNum(good.getOrderNum());
//                        System.out.println(newgood);
//                        goodsDao.insertOneGood(newgood);
//                    }else{
//                        goodByName.setCategoryId(id);
//                        goodsDao.updateGood(goodByName);
//                    }
//                    System.out.println(goodByName);
//                    System.out.println("======");
//                }
//            }
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    public void test() throws JsonProcessingException {
        String response = HttpRequest.get("http://pcapi-xiaotuxian-front.itheima.net/category?id=19999999").body();
        ObjectMapper mapper = new ObjectMapper();
        VO vo = mapper.readValue(response, VO.class);
        Item result = vo.getResult();
        List<CategorySP> children = result.getChildren();
        for (CategorySP child : children) {
            List<Goods> goods = child.getGoods();
            for (Goods good : goods) {
                Goods goodByName = goodsDao.getGoodByName(good.getName());
                if (goodByName == null){
                    System.out.println(good);
                    goodsDao.insertOneGood(good);
                }else {
                    System.out.println(goodByName.getId());
                }

            }
        }
    }
}
