package com.lioch3cooh.glaciersmall.service.serviceImpl;

import com.lioch3cooh.glaciersmall.dao.SpecialDao;
import com.lioch3cooh.glaciersmall.entity.Special;
import com.lioch3cooh.glaciersmall.service.SpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialServiceImpl implements SpecialService {
    @Autowired
    private SpecialDao specialDao;

    @Override
    public int insertOneSpecial(Special special) {
        return specialDao.insertOneSpecial(special);
    }
}
