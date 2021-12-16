package com.lioch3cooh.glaciersmall.controller;

import com.github.wxpay.sdk.WXPay;
import com.lioch3cooh.glaciersmall.config.MyWxPay;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/order")
@ResponseBody
public class OrderController {

    @GetMapping("/pay")
    public void PaySuccess() {

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
