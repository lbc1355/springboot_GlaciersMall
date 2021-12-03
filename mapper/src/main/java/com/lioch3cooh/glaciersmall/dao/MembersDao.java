package com.lioch3cooh.glaciersmall.dao;


import com.lioch3cooh.glaciersmall.entity.Members;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface MembersDao {
    List<Members> getAllMembers();
}
