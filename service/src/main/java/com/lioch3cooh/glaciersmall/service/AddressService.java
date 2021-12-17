package com.lioch3cooh.glaciersmall.service;

import com.lioch3cooh.glaciersmall.entity.UserAddresses;
import com.lioch3cooh.glaciersmall.vo.VoResult;

public interface AddressService {
    /**
     * 添加地址
     * @param addresses
     * @return
     */
    VoResult addAddress(UserAddresses addresses);

    VoResult updateAddress(UserAddresses userAddresses);
}
