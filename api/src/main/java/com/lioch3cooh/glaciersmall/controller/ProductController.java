package com.lioch3cooh.glaciersmall.controller;

import com.lioch3cooh.glaciersmall.entity.vo.VoProduct;
import com.lioch3cooh.glaciersmall.service.GoodSkuService;
import com.lioch3cooh.glaciersmall.vo.VoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@RequestMapping("/goods")
@ResponseBody
@Controller
public class ProductController {

    @Autowired
    private GoodSkuService goodSkuService;


    @GetMapping("")
    public VoResult getVoProduct(Integer id) {
        VoResult voResult = goodSkuService.getVoProduct(id);

        return voResult;
    }
}
