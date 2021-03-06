package com.lioch3cooh.glaciersmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.github.wxpay.sdk.WXPay;
import com.lioch3cooh.glaciersmall.config.AlipayConfig;
import com.lioch3cooh.glaciersmall.config.MyWxPay;
import com.lioch3cooh.glaciersmall.dao.OrderDao;
import com.lioch3cooh.glaciersmall.entity.Order;
import com.lioch3cooh.glaciersmall.unity.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@CrossOrigin
@Controller
public class PayController {

    @Autowired
    private AlipayConfig alipayConfig;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private OrderDao orderDao;

    @GetMapping("/pay/aliPay")
    public void aliPay(@RequestParam("orderId") String orderId,
                       @RequestParam("redirect") String redirect,
                       HttpServletResponse response) {
        AlipayClient client = new DefaultAlipayClient(
                alipayConfig.getGatewayUrl(),
                alipayConfig.getAppId(),
                alipayConfig.getMerchantPrivateKey(),
                alipayConfig.getFormat(),
                alipayConfig.getCharset(),
                alipayConfig.getAlipayPublicKey(),
                alipayConfig.getSignType()
        );


        Order order = (Order) redisTemplate.opsForValue().get("order" + orderId);
        if (order != null) {
            // ?????????????????????????????????????????????????????????????????????
            String outTradeNo = orderId;
            // ?????????????????????
            String subject = "GlaciersInMall";
            // ?????????????????????
            String totalAmount = new String(String.valueOf(order.getPayMoney()));
            try {
                String createTime = order.getCreateTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // ??????????????????
                Date OutTime = sdf.parse(createTime);
                // ????????????
                long dueToTime = OutTime.getTime() + (1000 * 60 * 30);
                // ????????????
                long nowTime = System.currentTimeMillis();

                int difference = (int) ((dueToTime - nowTime) / 1000 / 60);

                // ???????????? ??????
                String timeoutExpress = difference + "m";
                System.out.println("???????????????????????????" + timeoutExpress);

                // ??????????????? ??????
                String productCode = "FAST_INSTANT_TRADE_PAY";

                // ????????????????????????
                AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
                // ????????????????????????
                request.setNotifyUrl(alipayConfig.getNotifyUrl());
                // ??????????????????
                request.setReturnUrl(redirect + "?orderId=" + orderId + "&payResult=" + true);
                System.out.println(redirect);


                // ????????????????????????
                AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
//                model.setOutTradeNo(outTradeNo);
//                model.setSubject(subject);
//                model.setTotalAmount(totalAmount);
                model.setTimeoutExpress(timeoutExpress);
                request.setBizModel(model);

                JSONObject bizContent = new JSONObject();
                bizContent.put("out_trade_no", outTradeNo);
                bizContent.put("total_amount", totalAmount);
                bizContent.put("subject", subject);
                bizContent.put("product_code", productCode);
                request.setBizContent(bizContent.toString());

                String form = client.pageExecute(request).getBody();
                response.setContentType("text/html;charset=" + alipayConfig.getCharset());
                //????????????????????????html???????????????
                response.getWriter().write(form);
                response.getWriter().flush();
                response.getWriter().close();
            } catch (AlipayApiException | IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else {
            response.setContentType("text/html;charset=" + alipayConfig.getCharset());
            //????????????????????????html???????????????
            try {
                response.getWriter().write("???????????????");
                response.getWriter().flush();
                response.getWriter().close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }


    @PostMapping("/alipay/success")
    @Transactional(rollbackFor = {Exception.class})
    public void aliPaySuccess(HttpServletRequest request,
                              @RequestParam("trade_status") String tradeStatus,
                              @RequestParam("total_amount") Double totalAmount,
                              @RequestParam("buyer_pay_amount") String buyerPayAmount,
                              @RequestParam("out_trade_no") String outTradeno,
                              @RequestParam("receipt_amount") String receiptAmount) {

        System.out.println(tradeStatus);

        // ??????????????????
        if ("TRADE_SUCCESS".equals(tradeStatus)) {
            Order order = orderDao.getOrder(outTradeno);
            if (order.getPayMoney().equals(totalAmount)) {
                System.out.println("????????????");
            }
            orderDao.updateOrderStatus(Order.ORDER_SUCCESS, outTradeno);
            System.out.println("??????????????????");
            redisTemplate.delete("order" + outTradeno);
        }

    }

    @GetMapping("/wxpay")
    public void WXPay() {

        MyWxPay config = new MyWxPay();
        Map<String, String> map = new HashMap();

        map.put("body", "test"); //????????????
        map.put("out_trade_no", "1639283057386");//?????????????????????????????????????????????????????????????????????
        map.put("fee_type", "CNY"); //????????????
        map.put("total_fee", 1 + ""); //????????????
        map.put("trade_type", "NATIVE");//????????????
        map.put("notify_url", "/order/pay");

        WXPay wxPay = new WXPay(config);
        Map<String, String> stringStringMap = null;
        try {
            stringStringMap = wxPay.unifiedOrder(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(stringStringMap);
    }
}
