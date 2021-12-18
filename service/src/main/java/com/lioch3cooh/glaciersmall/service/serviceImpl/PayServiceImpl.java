package com.lioch3cooh.glaciersmall.service.serviceImpl;
import com.lioch3cooh.glaciersmall.service.PayService;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {



    @Override
    public String aliPay() {

//        AlipayClient client = new DefaultAlipayClient(
//                alipayConfig.getGatewayUrl(),
//                alipayConfig.getAppId(),
//                alipayConfig.getMerchantPrivateKey(),
//                alipayConfig.getFormat(),
//                alipayConfig.getCharset(),
//                alipayConfig.getAlipayPublicKey(),
//                alipayConfig.getSignType()
//        );
//
//
//        SnowFlake snowFlake = new SnowFlake(1, 1);
//        // 商户订单号，商户网站订单系统中唯一订单号，必填
//        String outTradeNo = String.valueOf(snowFlake.nextId());
//        // 订单名称，必填
//        String subject = "测试";
//
//        // 付款金额，必填
//        String totalAmount = new String("100.00");
//
//        // 商品描述，可空
//        String body = new String("这是一个支付宝沙箱测试");
//
//        // 超时时间 可空
//        String timeoutExpress = "30m";
//
//        // 销售产品码 必填
//        String productCode = "101010";
//
//        // 封装请求支付信息
//        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
//        // 设置异步通知地址
//        request.setNotifyUrl(alipayConfig.getNotifyUrl());
//        // 设置同步地址
//        request.setReturnUrl(alipayConfig.getReturnUrl());
//
//
//        // 封装请求支付信息
//        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
//        model.setOutTradeNo(outTradeNo);
//        model.setSubject(subject);
//        model.setTotalAmount("100.00");
//        model.setBody(body);
//        model.setTimeoutExpress("5m");
//        model.setProductCode("QUICK_WAP_WAY");
//        request.setBizModel(model);
//
//
//        try {
//            String form = client.pageExecute(request).getBody();
//
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//        }
//

        return "";
    }
}
