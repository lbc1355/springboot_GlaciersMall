package com.lioch3cooh.glaciersmall.dao;

import com.lioch3cooh.glaciersmall.entity.Obeans.SkuStatus;
import com.lioch3cooh.glaciersmall.entity.Sku;
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

    /**
     * 查询一个SKU 的状态 ("nowPrice","oldPrice","stock","discount","isEffective" )
     * @param skuId
     */
    SkuStatus getSkuStatus(Integer skuId);


     Sku querySkuBySkuId(Integer skuId);
}
