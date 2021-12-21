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

import io.renren.modules.generator.entity.GoodsSkuEntity;
import io.renren.modules.generator.service.GoodsSkuService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * SKU; InnoDB free: 7168 kB
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-12-21 15:04:29
 */
@RestController
@RequestMapping("generator/goodssku")
public class GoodsSkuController {
    @Autowired
    private GoodsSkuService goodsSkuService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:goodssku:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = goodsSkuService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:goodssku:info")
    public R info(@PathVariable("id") Integer id){
		GoodsSkuEntity goodsSku = goodsSkuService.getById(id);

        return R.ok().put("goodsSku", goodsSku);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:goodssku:save")
    public R save(@RequestBody GoodsSkuEntity goodsSku){
		goodsSkuService.save(goodsSku);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:goodssku:update")
    public R update(@RequestBody GoodsSkuEntity goodsSku){
		goodsSkuService.updateById(goodsSku);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:goodssku:delete")
    public R delete(@RequestBody Integer[] ids){
		goodsSkuService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
