package com.lioch3cooh.glaciersmall.service.serviceImpl;

import com.lioch3cooh.glaciersmall.dao.AddressDao;
import com.lioch3cooh.glaciersmall.entity.UserAddresses;
import com.lioch3cooh.glaciersmall.service.AddressService;
import com.lioch3cooh.glaciersmall.unity.VoResultUnit;
import com.lioch3cooh.glaciersmall.vo.VoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public VoResult addAddress(UserAddresses userAddresses) {
        VoResult defaultVoRes = VoResultUnit.getDefaultVoRes();

        try {
            int id = addressDao.insertAddress(userAddresses);
            Map result = new HashMap();
            result.put("id", userAddresses.getId());

            defaultVoRes = VoResultUnit.getSuccessVoRes(defaultVoRes, result);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return defaultVoRes;
    }

    @Override
    public VoResult updateAddress(UserAddresses userAddresses) {
        VoResult defaultVoRes = VoResultUnit.getDefaultVoRes();

        try {
            addressDao.updateAddress(userAddresses);
            Map result = new HashMap();
            result.put("id", userAddresses.getId());
            defaultVoRes = VoResultUnit.getSuccessVoRes(defaultVoRes, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultVoRes;
    }
}
