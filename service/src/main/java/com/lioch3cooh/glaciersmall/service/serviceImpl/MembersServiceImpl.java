package com.lioch3cooh.glaciersmall.service.serviceImpl;

import com.lioch3cooh.glaciersmall.dao.MembersDao;

import com.lioch3cooh.glaciersmall.entity.Members;
import com.lioch3cooh.glaciersmall.entity.vo.VoMember;
import com.lioch3cooh.glaciersmall.service.MembersService;
import com.lioch3cooh.glaciersmall.unity.MD5Utils;
import com.lioch3cooh.glaciersmall.unity.VoResultUnit;
import com.lioch3cooh.glaciersmall.vo.VoResult;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MembersServiceImpl implements MembersService {

    @Autowired
    private MembersDao membersDao;

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
}
