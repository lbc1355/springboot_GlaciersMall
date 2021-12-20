package com.lioch3cooh.glaciersmall.service.serviceImpl;

import com.lioch3cooh.glaciersmall.dao.CartDao;
import com.lioch3cooh.glaciersmall.dao.GoodSkuDao;
import com.lioch3cooh.glaciersmall.dao.GoodsDao;
import com.lioch3cooh.glaciersmall.dao.SpecificationsDao;
import com.lioch3cooh.glaciersmall.entity.Cart;
import com.lioch3cooh.glaciersmall.entity.Goods;
import com.lioch3cooh.glaciersmall.entity.Obeans.CartSkuData;
import com.lioch3cooh.glaciersmall.entity.Obeans.ProductSkusSpecs;
import com.lioch3cooh.glaciersmall.entity.RequestBodyEnity.MergeCart;
import com.lioch3cooh.glaciersmall.entity.Sku;
import com.lioch3cooh.glaciersmall.service.CartService;
import com.lioch3cooh.glaciersmall.unity.VoResultUnit;
import com.lioch3cooh.glaciersmall.vo.VoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private GoodSkuDao goodSkuDao;

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private SpecificationsDao specificationsDao;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public VoResult mergeCart(List<MergeCart> mergeCartList, String memberId) {

        VoResult defaultVoRes = VoResultUnit.getDefaultVoRes();
        try {
            //  遍历
            for (MergeCart mergeCart : mergeCartList) {
                // 查询用户购物车中是否有该商品
                Cart cart = cartDao.queryCart(memberId, mergeCart.getSkuId());
                //获取该商品信息
                Sku sku = goodSkuDao.querySkuBySkuId(mergeCart.getSkuId());
                // 没有 则添加
                if (cart == null) {
                    // 赋值
                    cart = new Cart();
                    cart.setCount(mergeCart.getCount());
                    cart.setMemberId(memberId);
                    cart.setPrice(sku.getPrice());
                    cart.setSelected(mergeCart.getSelected() ? 1 : 0);
                    cart.setSkuId(mergeCart.getSkuId());
                    cart.setMemberId(memberId);
                    // 插入购物车
                    cartDao.insertCart(cart);
                } else {
                    // 累加
                    cart.setPrice(sku.getPrice());
                    cart.setCount(cart.getCount() + mergeCart.getCount());
                    cart.setSelected(mergeCart.getSelected() ? 1 : 0);
                    cartDao.updateCart(cart);
                }
            }
            defaultVoRes = VoResultUnit.getSuccessVoRes(defaultVoRes, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultVoRes;
    }

    @Override
    public VoResult findCart(String memberId) {
        VoResult voRes = VoResultUnit.getDefaultVoRes();
        List<CartSkuData> cartSkuDataList = new ArrayList<>();
        CartSkuData cartSkuData = null;
        // 获取用户购物车信息
        List<Cart> carts = cartDao.listCart(memberId);
        StringBuilder stringBuilder = new StringBuilder();
        for (Cart cart : carts) {
            Integer skuId = cart.getSkuId();
            // 获取SKU
            Sku sku = goodSkuDao.querySkuBySkuId(skuId);
            // 根据sku获取good
            Goods goods = goodsDao.getGoodsBySkuId(skuId);
            // 获取sku属性
            List<ProductSkusSpecs> productSkusSpecs = specificationsDao.listSkuSpecs(skuId);
            // 清空stringBuilder
            stringBuilder.delete(0, stringBuilder.length());
            // 拼接规格
            for (ProductSkusSpecs productSkusSpec : productSkusSpecs) {
                stringBuilder.append(productSkusSpec.getName())
                        .append(":")
                        .append(productSkusSpec.getValueName())
                        .append(" ");
            }
            cartSkuData = new CartSkuData();
            cartSkuData.setAttrsText(stringBuilder.toString());
            cartSkuData.setCount(cart.getCount());
            cartSkuData.setDiscount(null);
            cartSkuData.setId(sku.getGoodId());
            // 是否被收藏
            cartSkuData.setIsCollect(false);
            cartSkuData.setIsEffective(sku.getIsEffective() == 0 ? false : true);
            cartSkuData.setName(sku.getName());
            cartSkuData.setNowOriginalPrice(cart.getPrice());
            // sku现在的价格
            cartSkuData.setNowPrice(sku.getPrice());
            cartSkuData.setSkuId(skuId);
            cartSkuData.setPicture(goods.getPicture());
            cartSkuData.setPostFee(0);
            // 加入购物车时候的价格
            cartSkuData.setPrice(cart.getPrice());
            cartSkuData.setSelected(cart.getSelected() == 0 ? false : true);
            cartSkuData.setSkuId(sku.getId());
            cartSkuData.setSpecs(productSkusSpecs);
            cartSkuData.setStock(sku.getInventory());
            cartSkuDataList.add(cartSkuData);

        }
        voRes = VoResultUnit.getSuccessVoResList(voRes, cartSkuDataList);

        return voRes;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public VoResult deleteCart(Map<String, List<Integer>> ids, String memberId) {

        VoResult voResult = VoResultUnit.getDefaultVoRes();

        try {
            cartDao.deleteCart(ids, memberId);
            voResult = VoResultUnit.getSuccessVoRes(voResult, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return voResult;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public VoResult insertCart(Map<String, Integer> ids, String memberId) {
        VoResult voResult = VoResultUnit.getDefaultVoRes();

        // 如果值长度不为2  且 值不为空
        if (ids.size() == 2 && ids.get("skuId") != null && ids.get("count") != null) {
            Integer skuId = ids.get("skuId");
            Integer count = ids.get("count");

            // 查询用户购物车中是否有该商品
            Cart cart = cartDao.queryCart(memberId, skuId);
            //获取该商品信息
            Sku sku = goodSkuDao.querySkuBySkuId(skuId);
            // 没有 则添加
            if (cart == null) {
                // 赋值
                cart = new Cart();
                cart.setCount(count);
                cart.setMemberId(memberId);
                cart.setPrice(sku.getPrice());
                cart.setSelected(1);
                cart.setSkuId(skuId);
                cart.setMemberId(memberId);
                // 插入购物车
                cartDao.insertCart(cart);
            } else {
                // 累加
                cart.setPrice(sku.getPrice());
                cart.setCount(cart.getCount() + count);
                cartDao.updateCart(cart);
            }

            voResult = VoResultUnit.getSuccessVoRes(voResult, null);

        }

        return voResult;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public VoResult updateCart(Object count, Object selected, Integer skuId, String memberId) {
        VoResult defaultVoRes = VoResultUnit.getDefaultVoRes();

        try {
            Cart cart = cartDao.queryCart(memberId, skuId);

            if (count != null) {
                cart.setCount((int) count);
            }
            if (selected != null) {
                boolean s = (boolean) selected;
                cart.setSelected(s ? 1 : 0);
            }
            cartDao.updateCart(cart);

            defaultVoRes = VoResultUnit.getSuccessVoRes(defaultVoRes, null);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return defaultVoRes;
    }


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public VoResult checkAllCart(Object selected, Object ids, String memberId) {
        VoResult defaultVoRes = VoResultUnit.getDefaultVoRes();
        try {
            if (selected != null && ids != null) {
                boolean s = (boolean) selected;
                List<Integer> ids1 = (List<Integer>) ids;
                Cart cart = null;
                for (Integer id : ids1) {
                    cart = cartDao.queryCart(memberId, id);
                    cart.setSelected(s ? 1 : 0);
                    cartDao.updateCart(cart);
                }
            }
            defaultVoRes = VoResultUnit.getSuccessVoRes(defaultVoRes, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return defaultVoRes;
    }


}
