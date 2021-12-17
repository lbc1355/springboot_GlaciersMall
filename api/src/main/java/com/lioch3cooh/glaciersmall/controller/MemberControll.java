package com.lioch3cooh.glaciersmall.controller;


import com.lioch3cooh.glaciersmall.entity.RequestBodyEnity.MergeCart;
import com.lioch3cooh.glaciersmall.service.CartService;
import com.lioch3cooh.glaciersmall.service.OrderService;
import com.lioch3cooh.glaciersmall.vo.VoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/member")
public class MemberControll {

    @Autowired
    private CartService cartService;

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
     * 生成订单
     * @return
     */
    @GetMapping("/order/pre")
    public VoResult createOrder(@RequestAttribute("memberId") String memberId) {
        return orderService.createOrder(memberId);
    }
}
