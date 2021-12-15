package com.lioch3cooh.glaciersmall.controller;


import com.lioch3cooh.glaciersmall.entity.RequestBodyEnity.MergeCart;
import com.lioch3cooh.glaciersmall.service.CartService;
import com.lioch3cooh.glaciersmall.unity.VoResultUnit;
import com.lioch3cooh.glaciersmall.vo.VoResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/member")
public class MemberControll {

    @Autowired
    private CartService cartService;

    @PostMapping("/cart/merge")
    public VoResult mergeCart(@RequestBody List<MergeCart> mergeCartList,
                              @RequestHeader(name = "Authorization") String token) {
        //验证token
        if (token != null) {
            String[] s = token.split(" ");
            if (s.length >= 2) {
                JwtParser parser = Jwts.parser();
                // 密码
                parser.setSigningKey("lioch3cooh");
                //解析token
                Jws<Claims> claimsJws = parser.parseClaimsJws(s[1]);
                // 获取token中的用户数据
                Claims body = claimsJws.getBody();
                String memberId = body.get("memberId", String.class);
                return cartService.mergeCart(mergeCartList, memberId);

            }
        }

        return VoResultUnit.getDefaultVoRes();
    }
}
