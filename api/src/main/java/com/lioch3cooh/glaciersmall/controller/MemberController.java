package com.lioch3cooh.glaciersmall.controller;


import com.lioch3cooh.glaciersmall.entity.Members;
import com.lioch3cooh.glaciersmall.entity.RequestBodyEnity.RBEMobileLogin;
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

    /**
     * 发送验证码
     *
     * @param mobile
     */
    @GetMapping("/login/code")
    public ResponseEntity userMobileLoginMsg(@RequestParam("mobile") String mobile) {
        // 登录成功容器
        VoResult defaultVoRes = VoResultUnit.getDefaultVoRes();
        // 响应
        ResponseEntity responseEntity = null;
        // 错误响应容器
        Map<String, Object> map = new HashMap<>();

        int flag = membersService.userMobileLoginMsg(mobile);

        // 发送成功
        if (flag == 2) {
            defaultVoRes = VoResultUnit.getSuccessVoRes(defaultVoRes, null);
            responseEntity = new ResponseEntity(defaultVoRes, HttpStatus.OK);
        } else if (flag == 1) {
            map.put("code", "17004");
            map.put("message", "验证码服务繁忙，请稍候再试");
            responseEntity = new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        } else {
            map.put("code", "17001");
            map.put("message", "用户不存在");
            responseEntity = new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }

    @PostMapping("/login/code")
    public ResponseEntity userMobileLogin(@RequestBody RBEMobileLogin rbeMobileLogin) {
        // 登录成功容器
        VoResult defaultVoRes = VoResultUnit.getDefaultVoRes();
        // 响应
        ResponseEntity responseEntity = null;
        // 错误响应容器
        Map<String, Object> map = new HashMap<>();

        // 验证码是否正确
        VoMember member = membersService.userMobileLogin(rbeMobileLogin);
        if (member != null) {
            // 账户不为空
            if (member.getId() != null && !"".equals(member.getId())) {
                defaultVoRes = VoResultUnit.getSuccessVoResObject(defaultVoRes, member);
                responseEntity = new ResponseEntity(defaultVoRes, HttpStatus.OK);
            }else{
                // 验证码错误
                map.put("code", "17003");
                map.put("message", "验证码错误");
                responseEntity = new ResponseEntity(map, HttpStatus.BAD_REQUEST);
            }
        }else{
            // 验证码已过期，或者根本没有发送过
            map.put("code", "17003");
            map.put("message", "验证码失效");
            responseEntity = new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }

        return responseEntity;
    }
}
