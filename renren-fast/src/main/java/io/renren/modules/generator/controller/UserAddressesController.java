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

import io.renren.modules.generator.entity.UserAddressesEntity;
import io.renren.modules.generator.service.UserAddressesService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 地址; InnoDB free: 7168 kB
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-12-21 15:04:29
 */
@RestController
@RequestMapping("generator/useraddresses")
public class UserAddressesController {
    @Autowired
    private UserAddressesService userAddressesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:useraddresses:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userAddressesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:useraddresses:info")
    public R info(@PathVariable("id") Integer id){
		UserAddressesEntity userAddresses = userAddressesService.getById(id);

        return R.ok().put("userAddresses", userAddresses);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:useraddresses:save")
    public R save(@RequestBody UserAddressesEntity userAddresses){
		userAddressesService.save(userAddresses);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:useraddresses:update")
    public R update(@RequestBody UserAddressesEntity userAddresses){
		userAddressesService.updateById(userAddresses);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:useraddresses:delete")
    public R delete(@RequestBody Integer[] ids){
		userAddressesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
