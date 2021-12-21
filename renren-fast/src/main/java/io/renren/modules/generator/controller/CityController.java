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

import io.renren.modules.generator.entity.CityEntity;
import io.renren.modules.generator.service.CityService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 城市; InnoDB free: 7168 kB
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-12-21 15:04:29
 */
@RestController
@RequestMapping("generator/city")
public class CityController {
    @Autowired
    private CityService cityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:city:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = cityService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{code}")
    @RequiresPermissions("generator:city:info")
    public R info(@PathVariable("code") Integer code){
		CityEntity city = cityService.getById(code);

        return R.ok().put("city", city);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:city:save")
    public R save(@RequestBody CityEntity city){
		cityService.save(city);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:city:update")
    public R update(@RequestBody CityEntity city){
		cityService.updateById(city);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:city:delete")
    public R delete(@RequestBody Integer[] codes){
		cityService.removeByIds(Arrays.asList(codes));

        return R.ok();
    }

}
