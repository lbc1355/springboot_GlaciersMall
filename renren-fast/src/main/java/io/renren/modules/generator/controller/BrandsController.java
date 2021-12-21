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

import io.renren.modules.generator.entity.BrandsEntity;
import io.renren.modules.generator.service.BrandsService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 品牌; InnoDB free: 7168 kB
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-12-21 15:04:29
 */
@RestController
@RequestMapping("generator/brands")
public class BrandsController {
    @Autowired
    private BrandsService brandsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:brands:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:brands:info")
    public R info(@PathVariable("id") Integer id){
		BrandsEntity brands = brandsService.getById(id);

        return R.ok().put("brands", brands);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:brands:save")
    public R save(@RequestBody BrandsEntity brands){
		brandsService.save(brands);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:brands:update")
    public R update(@RequestBody BrandsEntity brands){
		brandsService.updateById(brands);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:brands:delete")
    public R delete(@RequestBody Integer[] ids){
		brandsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
