package com.lioch3cooh.glaciersmall.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.github.wxpay.sdk.WXPay;
import com.lioch3cooh.glaciersmall.config.AlipayConfig;
import com.lioch3cooh.glaciersmall.config.MyWxPay;
import com.lioch3cooh.glaciersmall.service.OrderService;
import com.lioch3cooh.glaciersmall.service.PayService;
import com.lioch3cooh.glaciersmall.unity.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @Autowired
    private AlipayConfig alipayConfig;

    @GetMapping("/pay/aliPay")
    public void aliPay(@RequestParam("orderId") String orderId,
                       @RequestParam("redirect") String redirect,
                       HttpServletResponse response) {
        System.out.println(orderId);
        System.out.println(redirect);


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
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
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
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        request.setBizModel(model);


        try {
            String form = client.pageExecute(request).getBody();
            response.setContentType("text/html;charset=" + alipayConfig.getCharset());
            response.getWriter().write(form);//直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();
        } catch (AlipayApiException | IOException e) {
            e.printStackTrace();
        }


    }

    @GetMapping("/add")
    public void addOrder() {

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
