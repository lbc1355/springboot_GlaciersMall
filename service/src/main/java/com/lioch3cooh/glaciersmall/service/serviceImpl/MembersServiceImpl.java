package com.lioch3cooh.glaciersmall.service.serviceImpl;

import com.lioch3cooh.glaciersmall.dao.MembersDao;

import com.lioch3cooh.glaciersmall.entity.Members;
import com.lioch3cooh.glaciersmall.entity.RequestBodyEnity.RBEMobileLogin;
import com.lioch3cooh.glaciersmall.entity.vo.VoMember;
import com.lioch3cooh.glaciersmall.service.MembersService;
import com.lioch3cooh.glaciersmall.service.Utility.VerificationCodeUnit;
import com.lioch3cooh.glaciersmall.unity.MD5Utils;
import com.lioch3cooh.glaciersmall.unity.VoResultUnit;
import com.lioch3cooh.glaciersmall.vo.VoResult;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class MembersServiceImpl implements MembersService {

    @Autowired
    private MembersDao membersDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Members> getAllMembers() {

        return membersDao.getAllMembers();

    }

    @Override
    public VoMember loginAccount(String account, String password) {
        // 加密
        password = MD5Utils.md5(password);
        VoMember member = membersDao.loginAccount(account);
        // 用户不为空 则说明账户正确
        if (member != null) {
            // 如果密码正确
            if (password.equals(member.getPassword())) {
                // token
                Map<String, Object> data = new HashMap<>();
                JwtBuilder builder = Jwts.builder();
                String token = builder.setSubject(account)
                        // 设置token生成日期
                        .setIssuedAt(new Date())
                        //设置用户ID 为tokenID
                        .setId(member.getId())
                        // 存放其他信息
                        .setClaims(data)
                        // 一天后过期
                        .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)))
                        .signWith(SignatureAlgorithm.HS256, "lioch3cooh")
                        .compact();
                member.setToken(token);
                // 密码置空
                member.setPassword("");
            }
        } else {
            return null;
        }
        return member;
    }

    @Override
    public int userMobileLoginMsg(String mobile) {
        Members memberByMobile = membersDao.getMemberByMobile(mobile);

        // 如果是已注册用户
        if (memberByMobile != null) {
            // 发送验证码
            //int code = VerificationCodeUnit.sendCode(mobile);
            int code = 123456;
            // 如果结果不为0  则说明发送成功
            if (code != 0) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(mobile);
                stringBuilder.append(":LoginCode");
                String key = stringBuilder.toString();

                Map<String, String> map = new HashMap();
                map.put("code", String.valueOf(code));
                map.put("mobile", mobile);

                // 存入redis 中 设置过期时间为60S
                redisTemplate.opsForValue().set(key, map, 60, TimeUnit.SECONDS);
                //成功
                return 2;

            } else {
                // 验证码发送失败
                return 1;
            }
        }

        // 用户不存在
        return 0;
    }

    @Override
    public VoMember userMobileLogin(RBEMobileLogin rbeMobileLogin) {
        String mobile = rbeMobileLogin.getMobile();
        String code = rbeMobileLogin.getCode();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mobile);
        stringBuilder.append(":LoginCode");
        // 取验证码
        Object data = redisTemplate.opsForValue().get(stringBuilder.toString());
        if (data != null) {
            Map<String, String> map = (Map<String, String>) data;
            String correctCode = map.get("code");
            // 验证码正确
            if (correctCode.equals(code)) {
                Members memberByMobile = membersDao.getMemberByMobile(mobile);
                VoMember member = membersDao.loginAccount(memberByMobile.getAccount());

                // token
                Map<String, Object> result = new HashMap<>();
                JwtBuilder builder = Jwts.builder();
                String token = builder.setSubject(member.getAccount())
                        // 设置token生成日期
                        .setIssuedAt(new Date())
                        //设置用户ID 为tokenID
                        .setId(member.getId())
                        // 存放其他信息
                        .setClaims(result)
                        // 一天后过期
                        .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)))
                        .signWith(SignatureAlgorithm.HS256, "lioch3cooh")
                        .compact();

                member.setToken(token);
                // 密码置空
                member.setPassword("");

                return member;
            }
            return new VoMember();
        }

        // 没取到，过期或者未发送验证码
        return null;

    }

    @Override
    public Members getMemberByMobile(String mobile) {
        return null;
    }


}
