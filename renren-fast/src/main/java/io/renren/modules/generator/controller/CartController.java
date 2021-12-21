package io.renren.modules.generator.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.CartEntity;
import io.renren.modules.generator.service.CartService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 购物车; InnoDB free: 7168 kB
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-12-21 15:04:29
 */
@RestController
@RequestMapping("generator/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:cart:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = cartService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{skuId}")
    @RequiresPermissions("generator:cart:info")
    public R info(@PathVariable("skuId") Integer skuId){
		CartEntity cart = cartService.getById(skuId);

        return R.ok().put("cart", cart);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:cart:save")
    public R save(@RequestBody CartEntity cart){
		cartService.save(cart);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:cart:update")
    public R update(@RequestBody CartEntity cart){
		cartService.updateById(cart);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:cart:delete")
    public R delete(@RequestBody Integer[] skuIds){
		cartService.removeByIds(Arrays.asList(skuIds));

        return R.ok();
    }

}
