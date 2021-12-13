package com.lioch3cooh.glaciersmall.controller;


import com.github.kevinsawicki.http.HttpRequest;
import com.lioch3cooh.glaciersmall.entity.Members;
import com.lioch3cooh.glaciersmall.entity.vo.VoMember;
import com.lioch3cooh.glaciersmall.service.MembersService;
import com.lioch3cooh.glaciersmall.unity.VoResultUnit;
import com.lioch3cooh.glaciersmall.vo.VoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
@ResponseBody
public class MemberController {
    @Autowired
    private MembersService membersService;

    @PostMapping("/login")
    public ResponseEntity uerLogin(@RequestBody Members members) {

        // 登录成功容器
        VoResult defaultVoRes = VoResultUnit.getDefaultVoRes();
        // 响应
        ResponseEntity responseEntity = null;
        // 错误响应容器
        Map<String, Object> map = new HashMap<>();
        VoMember oneMember = membersService.loginAccount(members.getAccount(), members.getPassword());
        // 返回的不为空  则说明账号正确
        if (oneMember != null) {
            // 如果有token 则说明密码正确
            if (!"".equals(oneMember.getToken()) && oneMember.getToken() != null) {
                defaultVoRes = VoResultUnit.getSuccessVoResObject(defaultVoRes, oneMember);
                responseEntity = new ResponseEntity(defaultVoRes, HttpStatus.OK);
            } else {
                // 密码错误
                map.put("code", "17003");
                map.put("message", "无效的密码");
                responseEntity = new ResponseEntity(map, HttpStatus.BAD_REQUEST);
            }
            // 用户不存在
        } else {
            map.put("code", "17001");
            map.put("message", "用户不存在");
            responseEntity = new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @GetMapping("/login/code")
    public void userMobileLoginMsg(@RequestParam("mobile") String mobile) {

    }
}
