package com.lioch3cooh.glaciersmall;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kevinsawicki.http.HttpRequest;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.lioch3cooh.glaciersmall.config.AlipayConfig;
import com.lioch3cooh.glaciersmall.dao.CategoryDao;
import com.lioch3cooh.glaciersmall.dao.RegionDao;
import com.lioch3cooh.glaciersmall.dao.GoodsDao;
import com.lioch3cooh.glaciersmall.entity.Goods;
import com.lioch3cooh.glaciersmall.service.BrandsService;
import com.lioch3cooh.glaciersmall.service.HomeService;
import com.lioch3cooh.glaciersmall.service.SpecialService;
import com.lioch3cooh.glaciersmall.unity.SnowFlake;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    private RegionDao cityDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AlipayConfig alipayConfig;

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

    /**
     *
     */
    @Test
    public void SnowFlakeTest() throws ParseException {

//
//        String testCashTime = (String) redisTemplate.opsForValue().get("13522767896:LoginCode");
//
//        System.out.println(testCashTime);

        AlipayClient client = new DefaultAlipayClient(
                alipayConfig.getGatewayUrl(),
                alipayConfig.getAppId(),
                alipayConfig.getMerchantPrivateKey(),
                alipayConfig.getFormat(),
                alipayConfig.getCharset(),
                alipayConfig.getAlipayPublicKey(),
                alipayConfig.getSignType()
        );


        SnowFlake snowFlake = new SnowFlake(1, 1);
        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String outTradeNo = String.valueOf(snowFlake.nextId());
        // 订单名称，必填
        String subject = "测试";

        // 付款金额，必填
        String totalAmount = new String("100.00");

        // 商品描述，可空
        String body = new String("这是一个支付宝沙箱测试");

        // 超时时间 可空
        String timeoutExpress = "30m";

        // 销售产品码 必填
        String productCode = "101010";

        // 封装请求支付信息
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        // 设置异步通知地址
        request.setNotifyUrl(alipayConfig.getNotifyUrl());
        // 设置同步地址
        request.setReturnUrl(alipayConfig.getReturnUrl());


        // 封装请求支付信息
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(outTradeNo);
        model.setSubject(subject);
        model.setTotalAmount("100.00");
        model.setBody(body);
        model.setTimeoutExpress("5m");
        model.setProductCode("QUICK_WAP_WAY");
        request.setBizModel(model);


        try {
            String form = client.pageExecute(request).getBody();

        } catch (AlipayApiException e) {
            e.printStackTrace();
        }


    }


}
