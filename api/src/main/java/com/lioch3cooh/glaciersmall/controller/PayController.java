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
            // 商户订单号，商户网站订单系统中唯一订单号，必填
            String outTradeNo = orderId;
            // 订单名称，必填
            String subject = "GlaciersInMall";
            // 付款金额，必填
            String totalAmount = new String(String.valueOf(order.getPayMoney()));
            try {
                String createTime = order.getCreateTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // 创建订单时间
                Date OutTime = sdf.parse(createTime);
                // 到期时间
                long dueToTime = OutTime.getTime() + (1000 * 60 * 30);
                // 现在时间
                long nowTime = System.currentTimeMillis();

                int difference = (int) ((dueToTime - nowTime) / 1000 / 60);

                // 超时时间 可空
                String timeoutExpress = difference + "m";
                System.out.println("支付宝订单超时时间" + timeoutExpress);

                // 销售产品码 必填
                String productCode = "FAST_INSTANT_TRADE_PAY";

                // 封装请求支付信息
                AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
                // 设置异步通知地址
                request.setNotifyUrl(alipayConfig.getNotifyUrl());
                // 设置同步地址
                request.setReturnUrl(redirect + "?orderId=" + orderId + "&payResult=" + true);
                System.out.println(redirect);


                // 封装请求支付信息
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
                //直接将完整的表单html输出到页面
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
            //直接将完整的表单html输出到页面
            try {
                response.getWriter().write("订单已超时");
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

        // 如果支付成功
        if ("TRADE_SUCCESS".equals(tradeStatus)) {
            Order order = orderDao.getOrder(outTradeno);
            if (order.getPayMoney().equals(totalAmount)) {
                System.out.println("金钱相等");
            }
            orderDao.updateOrderStatus(Order.ORDER_SUCCESS, outTradeno);
            System.out.println("状态更改完成");
            redisTemplate.delete("order" + outTradeno);
        }

    }

    @GetMapping("/wxpay")
    public void WXPay() {

        MyWxPay config = new MyWxPay();
        Map<String, String> map = new HashMap();

        map.put("body", "test"); //商品描述
        map.put("out_trade_no", "1639283057386");//使用当前用户订单编号作为当前支付交易的交易编号
        map.put("fee_type", "CNY"); //支付币种
        map.put("total_fee", 1 + ""); //支付金额
        map.put("trade_type", "NATIVE");//交易类型
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
