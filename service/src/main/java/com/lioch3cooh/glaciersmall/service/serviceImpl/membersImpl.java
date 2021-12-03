package com.lioch3cooh.glaciersmall.service.serviceImpl;

import com.lioch3cooh.glaciersmall.dao.MembersDao;

import com.lioch3cooh.glaciersmall.entity.Members;
import com.lioch3cooh.glaciersmall.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class membersImpl implements MembersService {

    @Autowired
    private MembersDao membersDao;

    @Override
    public List<Members> getAllMembers() {

        return membersDao.getAllMembers();
        
    }
}
