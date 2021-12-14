package com.lioch3cooh.glaciersmall.dao;


import com.lioch3cooh.glaciersmall.entity.Members;
import com.lioch3cooh.glaciersmall.entity.vo.VoMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface MembersDao {
    List<Members> getAllMembers();

    /**
     * 用户账号密码登录
     * @param account
     * @return
     */
    VoMember loginAccount(String account);

    /**
     * 根据手机号查找用户名
     * @param mobile
     * @return
     */
    Members getMemberByMobile(@Param("mobile") String mobile);



}
