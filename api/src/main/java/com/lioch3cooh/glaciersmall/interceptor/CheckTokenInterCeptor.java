package com.lioch3cooh.glaciersmall.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lioch3cooh.glaciersmall.vo.VoResult;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 校验token 的 拦截器
 */
@Component
public class CheckTokenInterCeptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        System.out.println(request.getRequestURL());
        String authorization = request.getHeader("Authorization");

        if (authorization != null) {
            String token = authorization.split(" ").length >= 1 ? authorization.split(" ")[1] : null;
            System.out.println(token);
            if (token != null) {
                try {
                    JwtParser parser = Jwts.parser();
                    // 密码
                    parser.setSigningKey("lioch3cooh");
                    //解析token
                    Jws<Claims> claimsJws = parser.parseClaimsJws(token);
                    // 获取token中的用户数据
                    Claims body = claimsJws.getBody();
                    String memberId = body.get("memberId", String.class);
                    request.setAttribute("memberId", memberId);
                    return true;
                } catch (ExpiredJwtException e) {
                    e.printStackTrace();
                    doFailResponse(response, new VoResult(0, "登录过期，请重新登录", null));
                } catch (UnsupportedJwtException e) {
                    e.printStackTrace();
                    doFailResponse(response, new VoResult(0, "登录信息异常,请重新登录", null));
                } catch (Exception e) {
                    e.printStackTrace();
                    doFailResponse(response, new VoResult(0, "登录异常,请重新登录", null));
                }
            }
        }
        doFailResponse(response, new VoResult(0, "请先登录", null));
        return false;
    }


    private void doFailResponse(HttpServletResponse response, VoResult failVoRes) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        ObjectMapper objectMapper = new ObjectMapper();
        String s = objectMapper.writeValueAsString(failVoRes);

        PrintWriter writer = response.getWriter();
        writer.println(s);
        writer.flush();
        writer.close();
    }
}
