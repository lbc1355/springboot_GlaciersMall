package com.lioch3cooh.glaciersmall.controller;


import com.lioch3cooh.glaciersmall.service.GoodSkuService;
import com.lioch3cooh.glaciersmall.vo.VoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/goods")
public class ProductController {

    @Autowired
    private GoodSkuService goodSkuService;


    @GetMapping("")
    public VoResult getVoProduct(Integer id) {
        VoResult voResult = goodSkuService.getVoProduct(id);
        return voResult;
    }

    /**
     * 查询SKU 状态 是否失效
     * @param skuId
     * @return
     */
    @GetMapping("/stock/{skuId}")
    public VoResult getCartGoodsStatus(@PathVariable("skuId") Integer skuId) {
        VoResult skuStatus = goodSkuService.getSkuStatus(skuId);
        return skuStatus;
    }

    /**
     * 根据SKU id 获取物品的所有规格，以及所有SKU
     * @param skuId
     * @return
     */
    @GetMapping("/sku/{skuId}")
    public VoResult getGoodsSku(@PathVariable("skuId") Integer skuId) {
        VoResult goodsSku = goodSkuService.getGoodsSku(skuId);
        return goodsSku;
    }
}
