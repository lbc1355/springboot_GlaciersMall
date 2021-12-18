package com.lioch3cooh.glaciersmall.config;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AlipayConfig {
    @Value("${alipay.app_id}")
    private String appId;

    @Value("${alipay.merchant_private_key}")
    private String merchantPrivateKey;

    @Value("${alipay.alipay_public_key}")
    private String alipayPublicKey;

    @Value("${alipay.notify_url}")
    private String notifyUrl;

    @Value("${alipay.return_url}")
    private String returnUrl;

    @Value("${alipay.sign_type}")
    private String signType;

    @Value("${alipay.charset}")
    private String charset;

    @Value("${alipay.gatewayUrl}")
    private String gatewayUrl;

    @Value("${alipay.format}")
    private String Format;

    @Value("${alipay.log_path}")
    private String logPath;



}
