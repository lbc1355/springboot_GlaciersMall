package com.lioch3cooh.glaciersmall.service;

import com.lioch3cooh.glaciersmall.entity.Members;
import com.lioch3cooh.glaciersmall.entity.RequestBodyEnity.RBEMobileLogin;
import com.lioch3cooh.glaciersmall.entity.vo.VoMember;
import com.lioch3cooh.glaciersmall.vo.VoResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MembersService {
    List<Members> getAllMembers();

    /**
     * 用户账户密码登录
     */
    VoMember loginAccount(String account, String password);

    /**
     * 向手机发送验证码
     * @param mobile
     */
    int userMobileLoginMsg(String mobile);

    /**
     * 检测验证码是否正确
     * @param rbeMobileLogin
     */
    VoMember userMobileLogin(RBEMobileLogin rbeMobileLogin);


    Members getMemberByMobile(String mobile);


}
