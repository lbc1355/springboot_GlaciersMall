package com.lioch3cooh.glaciersmall.dao;


import com.lioch3cooh.glaciersmall.entity.Members;
import com.lioch3cooh.glaciersmall.entity.vo.VoMember;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface MembersDao {
    List<Members> getAllMembers();

    /**
     * 用户账号密码登录
     * @param account
     * @param password
     * @return
     */
    VoMember loginAccount(String account);
}
