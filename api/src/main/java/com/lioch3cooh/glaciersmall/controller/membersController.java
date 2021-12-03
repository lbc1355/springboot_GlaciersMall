package com.lioch3cooh.glaciersmall.controller;

import com.lioch3cooh.glaciersmall.entity.Members;
import com.lioch3cooh.glaciersmall.service.MembersService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/members")
@ResponseBody
@Api(value = "提供用户的登录，注册等操作",tags = "用户登录")
public class membersController {
    @Autowired
    MembersService service;

    @GetMapping("/getAll")
    public List<Members> getAllMembers() {
        return service.getAllMembers();
    }
}
