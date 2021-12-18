package com.lioch3cooh.glaciersmall.service.serviceImpl;

import com.lioch3cooh.glaciersmall.dao.AddressDao;
import com.lioch3cooh.glaciersmall.dao.CartDao;
import com.lioch3cooh.glaciersmall.dao.GoodSkuDao;
import com.lioch3cooh.glaciersmall.dao.SpecificationsDao;
import com.lioch3cooh.glaciersmall.entity.Obeans.ProductSkusSpecs;
import com.lioch3cooh.glaciersmall.entity.UserAddresses;
import com.lioch3cooh.glaciersmall.service.OrderService;
import com.lioch3cooh.glaciersmall.unity.SnowFlake;
import com.lioch3cooh.glaciersmall.unity.VoResultUnit;
import com.lioch3cooh.glaciersmall.vo.VoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private GoodSkuDao skuDao;

    @Autowired
    private CartDao cartDao;

    @Autowired
    private SpecificationsDao specificationsDao;

    @Autowired
    private AddressDao addressDao;

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
     * 生成订单
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

        VoResult defaultVoRes = VoResultUnit.getDefaultVoRes();

        if (deliveryTimeType != null && payType != null &&
                payChannel != null && buyerMessage != null && goods != null) {
            SnowFlake snowFlake = new SnowFlake(1, 1);

            String orderId = String.valueOf(snowFlake.nextId());
        }
        return defaultVoRes;
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
}
