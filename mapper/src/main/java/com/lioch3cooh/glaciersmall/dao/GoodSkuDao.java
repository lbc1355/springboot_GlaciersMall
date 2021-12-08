package com.lioch3cooh.glaciersmall.dao;

import com.lioch3cooh.glaciersmall.entity.vo.VoSku;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodSkuDao {
    /**
     * 获取一个SPU的SKU
     */
    VoSku listVoSKU(Integer goods_id);
}
