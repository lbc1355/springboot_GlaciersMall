package com.lioch3cooh.glaciersmall.service.serviceImpl;

import com.lioch3cooh.glaciersmall.dao.CartDao;
import com.lioch3cooh.glaciersmall.dao.GoodSkuDao;
import com.lioch3cooh.glaciersmall.entity.Cart;
import com.lioch3cooh.glaciersmall.entity.Obeans.SkuStatus;
import com.lioch3cooh.glaciersmall.entity.RequestBodyEnity.MergeCart;
import com.lioch3cooh.glaciersmall.entity.Sku;
import com.lioch3cooh.glaciersmall.service.CartService;
import com.lioch3cooh.glaciersmall.unity.VoResultUnit;
import com.lioch3cooh.glaciersmall.vo.VoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private GoodSkuDao goodSkuDao;

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
}
