package com.lioch3cooh.glaciersmall.service;

import com.lioch3cooh.glaciersmall.entity.Members;
import com.lioch3cooh.glaciersmall.entity.vo.VoMember;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MembersService {
    List<Members> getAllMembers();

    // 用户账户密码登录
    VoMember loginAccount(String account, String password);
}
