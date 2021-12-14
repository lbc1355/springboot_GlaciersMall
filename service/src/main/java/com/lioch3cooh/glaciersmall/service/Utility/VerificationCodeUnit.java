package com.lioch3cooh.glaciersmall.service.Utility;

import com.github.kevinsawicki.http.HttpRequest;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Map;

public class VerificationCodeUnit {
    /**
     * 用于给手机号发送验证码
     *
     * @return
     */
    public static int sendCode(String mobile) {
        //验证码
        int mobileCode = (int) ((Math.random() * 9 + 1) * 100000);
        // 信息模板
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("您的验证码是：");
        stringBuilder.append(mobileCode);
        stringBuilder.append("。请不要把验证码泄露给其他人。");
        String content = stringBuilder.toString();

        Map<String, String> data = new HashMap<>();
        data.put("account", "C10151335");
        data.put("password", "7fe2edd5c824852ec4e5a9faacda609c");
        data.put("mobile", mobile);
        data.put("content", content);


        String response = HttpRequest.post("http://106.ihuyi.com/webservice/sms.php?method=Submit").form(data).body();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(response);
            Element root = doc.getRootElement();

            String code = root.elementText("code");
            String msg = root.elementText("msg");
            String smsid = root.elementText("smsid");
            if ("2".equals(code)) {
                return mobileCode;
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
