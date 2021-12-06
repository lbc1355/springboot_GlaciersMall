package com.lioch3cooh.glaciersmall;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kevinsawicki.http.HttpRequest;
import com.lioch3cooh.glaciersmall.dao.CategoryDao;
import com.lioch3cooh.glaciersmall.dao.CityDao;
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
    @Autowired
    private CityDao cityDao;

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
                if (goodByName == null) {
                    System.out.println(good);
                    goodsDao.insertOneGood(good);
                } else {
                    System.out.println(goodByName.getId());
                }

            }
        }
    }

    @Test
    public void getCity() throws JsonProcessingException {
//        String response = HttpRequest.get("https://yjy-oss-files.oss-cn-zhangjiakou.aliyuncs.com/tuxian/area.json").body();
//        ObjectMapper mapper = new ObjectMapper();
//        List<Region> cities = mapper.readValue(response, new TypeReference<List<Region>>() {
//        });
//
//        com.lioch3cooh.glaciersmall.entity.Region c = null;
//        for (Region c1 : cities) {
//            // 省级
//            c = new com.lioch3cooh.glaciersmall.entity.Region();
//            c.setCode(c1.getCode());
//            c.setLevel(c1.getLevel());
//            c.setName(c1.getName());
//            // 插入到数据库
//            cityDao.insertOneRegion(c);
//            List<Region> c2List = c1.getAreaList();
//            // 市级
//            for (Region c2 : c2List) {
//                c.setCode(c2.getCode());
//                c.setLevel(c2.getLevel());
//                c.setName(c2.getName());
//                c.setParentId(c1.getCode());
//                com.lioch3cooh.glaciersmall.entity.Region oneCity = cityDao.getOneRegion(c.getCode());
//                if (oneCity == null){
//                    cityDao.insertOneRegion(c);
//                }else{
//                    System.out.println("已存在" + c);
//                }
//                // 插入到数据库
//                List<Region> c3List = c2.getAreaList();
//                // 县级
//                for (Region c3 : c3List) {
//                    c.setCode(c3.getCode());
//                    c.setLevel(c3.getLevel());
//                    c.setName(c3.getName());
//                    c.setParentId(c2.getCode());
//                    oneCity = cityDao.getOneRegion(c.getCode());
//                    if (oneCity == null){
//                        cityDao.insertOneRegion(c);
//                    }else{
//                        System.out.println("已存在" + c);
//                    }
//                }
//            }

    }


}
