package com.lioch3cooh.glaciersmall.service.serviceImpl;

import com.lioch3cooh.glaciersmall.dao.*;
import com.lioch3cooh.glaciersmall.entity.*;
import com.lioch3cooh.glaciersmall.entity.Obeans.ProductSkusSpecs;
import com.lioch3cooh.glaciersmall.entity.Obeans.SkuStatus;
import com.lioch3cooh.glaciersmall.service.OrderService;
import com.lioch3cooh.glaciersmall.unity.SnowFlake;
import com.lioch3cooh.glaciersmall.unity.VoResultUnit;
import com.lioch3cooh.glaciersmall.vo.VoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private GoodSkuDao skuDao;

    @Autowired
    private SpecificationsDao specificationsDao;

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private RedisTemplate redisTemplate;


  /*
        count: 1
        id: "3815060"
        name: "可以喝的水果面膜韩国蜂蜜柚子茶560克"
        picture: "https://yanxuan-item.nosdn.127.net/16e0f76367c3184ef64431a5676e69c0.png"
        price: "99.00"
        skuId: "300241801"

            attrsText: "规格:蜂蜜柚子茶560克*3瓶 "
            payPrice: "99.00"
            totalPayPrice: "99.00"
            totalPrice: "99.00"
         */

    /**
     * 生成结算信息
     *
     * @param memberId
     * @return 包含goods summary userAddresses
     */
    @Override
    public VoResult createOrder(String memberId) {
        VoResult defaultVoRes = VoResultUnit.getDefaultVoRes();

        // 折扣 discountPrice
        Double discountPrice = 0.0;
        // 商品总件数 goodsCount
        Integer goodsCount = 0;
        // 邮费 postFee
        Double postFee = 5.0;
        // 全部总价格 totalPrice
        Double totalPrice = 0.0;
        // 最终实际支付价格 totalPayPrice
        Double totalPayPrice = 0.0;

        try {
            // 获取到订单页面需要到商品信息
            List<Map> goods = skuDao.listSkuByMemberId(memberId);

            // goods 完善信息
            for (Map good : goods) {
                List<ProductSkusSpecs> specs =
                        specificationsDao.listSkuSpecs((Integer) (good.get("skuId")));
                String s = stitchingSpecification(specs);

                double payPrice = (double) (Math.round((double) good.get("price") * 100) * (int) good.get("count") / 100);
                good.put("attrsText", s);
                good.put("payPrice", payPrice);
                good.put("totalPayPrice", payPrice);
                good.put("totalPrice", payPrice);

                goodsCount += (int) good.get("count");
                totalPrice += payPrice;
            }

            totalPayPrice = postFee + totalPrice;
            // summary
            Map<String, Object> summary = new HashMap<>();
            summary.put("discountPrice", discountPrice);
            summary.put("goodsCount", goodsCount);
            summary.put("postFee", postFee);
            summary.put("totalPrice", totalPrice);
            summary.put("totalPayPrice", totalPayPrice);

            List<UserAddresses> address = addressDao.listAddress(memberId);

            Map<String, Object> result = new HashMap<>();
            result.put("goods", goods);
            result.put("summary", summary);
            result.put("userAddresses", address);
            defaultVoRes = VoResultUnit.getSuccessVoRes(defaultVoRes, result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return defaultVoRes;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public VoResult submitOrder(Map payload, String memberId) {
        /*
         * deliveryTimeType 1  配送时间类型，1为不限，2为工作日，3为双休或假日
         * payType 1  支付方式，1为在线支付，2为货到付款
         * payChannel 支付渠道：支付渠道，1支付宝、2微信--支付方式为在线支付时，传值，为货到付款时，不传值
         * buyerMessage 留言
         * goods [{skuId=4, count=3}, {skuId=7, count=3}]
         * addressId 1 所选地址Id
         */
        Object deliveryTimeType = payload.get("deliveryTimeType");
        Object payType = payload.get("payType");
        Object payChannel = payload.get("payChannel");
        Object buyerMessage = payload.get("buyerMessage");
        Object goods = payload.get("goods");
        Object addressId = payload.get("addressId");
        VoResult defaultVoRes = VoResultUnit.getDefaultVoRes();

        if (deliveryTimeType != null && payType != null &&
                payChannel != null && buyerMessage != null
                && goods != null && addressId != null) {

            try {
                List<Map<String, Integer>> skus = (List<Map<String, Integer>>) goods;

                // 查看库存
                Map<String, Object> skuResult = checkInventory(skus);
                // 如果sku条件不满足购买
                if (skuResult == null) {
                    return defaultVoRes;
                }
                // 锁定库存(减库存)
                lockingInventory(skus, memberId);
                // 生成订单ID
                Order order = getOreder(payload, skuResult, memberId);
                //　插入订单到数据库
                orderDao.insertOrder(order);
                //保存地址快照
                saveAddressSnapshot((int) addressId, order.getId());
                //保存商品快照
                saveSkuSnapshot(skus, order.getId());
                // 将订单号存入reids
                redisTemplate.opsForValue().set("order" + order.getId(), order, 30L, TimeUnit.MINUTES);


                Map<String, Object> result = new HashMap<>();
                result.put("id", order.getId());
                result.put("payType", order.getPayType());
                result.put("payChannel", order.getPayChannel());
                defaultVoRes = VoResultUnit.getSuccessVoRes(defaultVoRes, result);
            } catch (Exception e) {
                throw e;
            }

        }
        return defaultVoRes;
    }

    @Override
    public VoResult getOrderStatu(String orderId, String memberId) {
        VoResult defaultVoRes = VoResultUnit.getDefaultVoRes();
        Map result = new HashMap();
        Order order = null;
        Integer countdown = -1;
        try {
            // 先从reids中寻找
            Object o = redisTemplate.opsForValue().get("order" + orderId);
            if (o == null) {
                order = orderDao.getOrder(orderId);
                if (order == null) {
                    result.put("countdown", countdown);
                    result.put("payMoney", 0);
                    defaultVoRes.setResult(result);
                    return defaultVoRes;
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date createTimeDate = sdf.parse(order.getCreateTime());

            // 到期时间
            long dueToTime = createTimeDate.getTime() + (1000 * 60 * 30);
            // 现在时间
            long nowTime = System.currentTimeMillis();
            long l = dueToTime - nowTime;

            result.put("countdown", l / 1000);
            result.put("payMoney", order.getPayMoney());
            defaultVoRes = VoResultUnit.getSuccessVoRes(defaultVoRes, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultVoRes;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void setOrderOverdue(String orderId) {
        orderDao.setOrderTimeout(orderId);
    }

    /**
     * 设置订单状态
     *
     * @param i
     * @param orderId
     */
    @Override
    public void setOrderStatus(int i, String orderId) {
        orderDao.updateOrderStatus(i, orderId);
    }


    /**
     * 拼接规格
     *
     * @param specs
     * @return
     */
    public String stitchingSpecification(List<ProductSkusSpecs> specs) {
        StringBuilder attrsText = new StringBuilder();
        for (ProductSkusSpecs spec : specs) {
            attrsText.append(spec.getName());
            attrsText.append(":");
            attrsText.append(spec.getValueName());
            attrsText.append(" ");
        }

        return attrsText.toString();
    }


    /**
     * 查看sku 库存是否满足需要求
     *
     * @param skus
     * @return
     */
    private Map<String, Object> checkInventory(List<Map<String, Integer>> skus) {
        Map<String, Object> result = new HashMap();

        double totalMoney = 0;
        int totalCount = 0;
        for (Map<String, Integer> sku : skus) {

            Integer skuId = sku.get("skuId");
            Integer count = sku.get("count");
            SkuStatus skuStatus = skuDao.getSkuStatus(skuId);

            //如果sku失效 或者 库存不够
            if (skuStatus.getIsEffective() == 0 || count > skuStatus.getStock()) {
                return null;
            }
            // 计算总价格
            totalMoney += skuStatus.getNowPrice() * count;
            // 计算总件数
            totalCount += count;
        }
        result.put("totalMoney", totalMoney);
        result.put("totalCount", totalCount);

        return result;
    }


    /**
     * 提交订单 - 生成订单
     *
     * @param payload
     * @param skuResult
     * @param memberId
     * @return
     */
    private Order getOreder(Map payload, Map<String, Object> skuResult, String memberId) {

        Object deliveryTimeType = payload.get("deliveryTimeType");
        Object payType = payload.get("payType");
        Object payChannel = payload.get("payChannel");
        Object buyerMessage = payload.get("buyerMessage");

        SnowFlake snowFlake = new SnowFlake(1, 1);
        String orderId = String.valueOf(snowFlake.nextId());

        // 计算订单信息
        Order order = new Order();
        order.setId(orderId);
        order.setPayType((int) payType);
        order.setDeliveryTimeTypel((int) deliveryTimeType);
        order.setBuyerMessage((String) buyerMessage);
        order.setMemberId(memberId);
        order.setPostFee(5.0);
        // 实付金额
        order.setPayMoney((Double) skuResult.get("totalMoney") + order.getPostFee());
        // 金额合计
        order.setTotalMoney((Double) skuResult.get("totalMoney") + order.getPostFee());
        // 总数量
        order.setTotalNum((int) skuResult.get("totalCount"));
        // 支付渠道
        order.setPayChannel((int) payChannel);
        // 订单状态，1为待付款、2为待发货、3为待收货、4为待评价、5为已完成、6为已取消
        order.setOrderState(1);
        Date createTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        order.setCreateTime(sdf.format(createTime));

        System.out.println(order);
        return order;
    }

    /**
     * 提交订单 - 锁定库存
     *
     * @param skus
     * @param memberId
     */
    private void lockingInventory(List<Map<String, Integer>> skus, String memberId) {
        for (Map<String, Integer> sku : skus) {
            Integer skuId = sku.get("skuId");
            Integer count = sku.get("count");
            Sku newSku = skuDao.getSkuBySkuId(skuId);
            newSku.setInventory(newSku.getInventory() - count);
            skuDao.updateSku(newSku);
            System.out.println(newSku);
        }
    }


    /**
     * 提交订单 - 保存地址快照
     *
     * @param addressId
     * @param orderId
     */
    private void saveAddressSnapshot(int addressId, String orderId) {
        UserAddresses addresses = addressDao.queryAddrById(addressId);
        AddressSnapshot snapshot = new AddressSnapshot();

        snapshot.setOrderId(orderId);
        snapshot.setAddress(addresses.getAddress());
        snapshot.setAdressId(addresses.getId());
        snapshot.setFullLocation(addresses.getFullLocation());
        snapshot.setContact(addresses.getContact());
        snapshot.setReceiver(addresses.getReceiver());
        System.out.println(snapshot);
        int i = orderDao.insertAddressSnap(snapshot);

    }


    /**
     * 提交订单 - 保存商品快照
     *
     * @param skus
     * @param orderId
     */
    private void saveSkuSnapshot(List<Map<String, Integer>> skus, String orderId) {
        for (Map<String, Integer> sku : skus) {

            Integer skuId = sku.get("skuId");
            Integer count = sku.get("count");

            SkuSnaoshot snaoshot = new SkuSnaoshot();

            Sku sku1 = skuDao.querySkuBySkuId(skuId);
            Goods good = goodsDao.getGoodsBySkuId(skuId);

            snaoshot.setGoodId(sku1.getGoodId());
            snaoshot.setSkuId(skuId);
            snaoshot.setName(sku1.getName());
            snaoshot.setPrice(sku1.getPrice());
            snaoshot.setCount(count);
            snaoshot.setPicture(good.getPicture());
            snaoshot.setOrderId(orderId);

            String specsText = specsText(specificationsDao.listSkuSpecs(skuId));
            snaoshot.setSpecsText(specsText);

            System.out.println(snaoshot);
            orderDao.insertSkuSnap(snaoshot);
        }

    }

    public String specsText(List<ProductSkusSpecs> productSkusSpecs) {
        StringBuilder stringBuilder = new StringBuilder();
        // 拼接规格
        for (ProductSkusSpecs productSkusSpec : productSkusSpecs) {
            stringBuilder.append(productSkusSpec.getName())
                    .append(":")
                    .append(productSkusSpec.getValueName())
                    .append(" ");
        }

        return stringBuilder.toString();
    }


}
