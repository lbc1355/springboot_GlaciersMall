package com.lioch3cooh.glaciersmall.dao;

import com.lioch3cooh.glaciersmall.entity.vo.VoSku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodSkuDao {
    /**
     * 获取一个SPU的SKU
     */
    List<VoSku> listVoSKU(@Param("goodsId") Integer goodsId);
}
