package com.lioch3cooh.glaciersmall.dao;

import com.lioch3cooh.glaciersmall.entity.Obeans.SkuStatus;
import com.lioch3cooh.glaciersmall.entity.Sku;
import com.lioch3cooh.glaciersmall.entity.vo.VoSku;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodSkuDao {
    /**
     * 获取一个SPU的SKU
     */
    List<VoSku> listVoSKU(@Param("goodsId") Integer goodsId);

    /**
     * 查询一个SKU 的状态 ("nowPrice","oldPrice","stock","discount","isEffective" )
     *
     * @param skuId
     */
    SkuStatus getSkuStatus(Integer skuId);



    Sku getSkuBySkuId(@Param("skuId") Integer skuId);

    /**
     * 通过SkuId 查询 sku
     *
     * @param skuId
     * @return
     */
    Sku querySkuBySkuId(Integer skuId);


    /**
     * 用户ID 返回订单页面所需要的商品信息
     *
     * @return
     */
    @MapKey("skuId")
    List<Map> listSkuByMemberId(@Param("memberId") String memberId);

    /**
     * 更新sku
     * @param newSku
     */
    void updateSku(@Param("sku") Sku newSku);
}
