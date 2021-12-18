package com.lioch3cooh.glaciersmall.controller;


import com.lioch3cooh.glaciersmall.dao.AddressDao;
import com.lioch3cooh.glaciersmall.entity.RequestBodyEnity.MergeCart;
import com.lioch3cooh.glaciersmall.entity.UserAddresses;
import com.lioch3cooh.glaciersmall.service.AddressService;
import com.lioch3cooh.glaciersmall.service.CartService;
import com.lioch3cooh.glaciersmall.service.OrderService;
import com.lioch3cooh.glaciersmall.unity.VoResultUnit;
import com.lioch3cooh.glaciersmall.vo.VoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/member")
public class MemberControll {

    @Autowired
    private CartService cartService;

    @Autowired
    private AddressService addressService;

    /**
     * 用户登录时 将上传过来的本地购物车合并到购物车表中
     *
     * @param mergeCartList
     * @return
     */
    @PostMapping("/cart/merge")
    public VoResult mergeCart(@RequestBody List<MergeCart> mergeCartList,
                              @RequestAttribute("memberId") String memberId) {
        return cartService.mergeCart(mergeCartList, memberId);

    }

    /**
     * 获取用户的购物信息
     *
     * @return
     */
    @GetMapping("/cart")
    public VoResult findCart(@RequestAttribute("memberId") String memberId) {

        return cartService.findCart(memberId);
    }


    /**
     * 删除购物车内的商品
     *
     * @param ids
     * @param memberId
     * @return
     */
    @DeleteMapping("/cart")
    public VoResult deleteCart(@RequestBody Map<String, List<Integer>> ids, @RequestAttribute("memberId") String memberId) {
        // 要删除的SKUID
        return cartService.deleteCart(ids, memberId);
    }


    /**
     * 插入一个商品到购物车
     *
     * @param ids
     * @param memberId
     * @return
     */
    @PostMapping("/cart")
    public VoResult insertCart(@RequestBody Map<String, Integer> ids,
                               @RequestAttribute("memberId") String memberId) {

        return cartService.insertCart(ids, memberId);
    }


    /**
     * 修改购物车商品（状态，数量）
     *
     * @param payload
     * @param skuId
     * @param memberId
     * @return
     */
    @PutMapping("/cart/{skuId}")
    public VoResult updateCart(@RequestBody Map<String, Object> payload,
                               @PathVariable("skuId") Integer skuId,
                               @RequestAttribute("memberId") String memberId) {

        Object count = payload.get("count");
        Object selected = payload.get("selected");

        return cartService.updateCart(count, selected, skuId, memberId);
    }

    /**
     * 全部选中&取消全选
     *
     * @param payload
     * @param memberId
     * @return
     */
    @PutMapping("/cart/selected")
    public VoResult checkAllCart(@RequestBody Map<String, Object> payload,
                                 @RequestAttribute("memberId") String memberId) {
        Object selected = payload.get("selected");
        Object ids = payload.get("ids");
        return cartService.checkAllCart(selected, ids, memberId);
    }


    /*这里是订单业务*/

    @Autowired
    private OrderService orderService;


    /**
     * 订单 -结算信息
     *
     * @return
     */
    @GetMapping("/order/pre")
    public VoResult createOrder(@RequestAttribute("memberId") String memberId) {
        return orderService.createOrder(memberId);
    }




    /* 这里是地址业务 */

    /**
     * 添加地址
     *
     * @param memberId
     * @return
     */
    @PostMapping("/address")
    public VoResult addAddress(@RequestBody UserAddresses userAddresses,
                               @RequestAttribute("memberId") String memberId) {
        userAddresses.setMemberId(memberId);

        return addressService.addAddress(userAddresses);
    }

    /**
     * 修改用户地址
     *
     * @return
     */
    @PutMapping("/address/{addressId}")
    public VoResult editAddress(@RequestBody UserAddresses userAddresses,
                                @PathVariable("addressId") Integer id,
                                @RequestAttribute("memberId") String memberId) {


        return addressService.updateAddress(userAddresses);
    }



    /* 这里是 支付业务 */

    /**
     * 提交订单
     *
     * @param memberId
     * @return
     */
    @PostMapping("/order")
    public VoResult submitOrder(@RequestBody Map payload,
                                @RequestAttribute("memberId") String memberId) {

        return  orderService.submitOrder(payload, memberId);
    }

    /**
     * 查询订单详细信息
     *
     * @param orderId
     * @param memberId
     * @return
     */
    @GetMapping("/order/{orderId}")
    public VoResult findOrderDetail(@PathVariable("orderId") String orderId,
                                    @RequestAttribute("memberId") String memberId) {

        VoResult voResult = VoResultUnit.getDefaultVoRes();

        Map result = new HashMap();
        result.put("countdown", 500);
        result.put("payMoney", 1999.00);
        voResult.setResult(result);

        return voResult;
    }
}
