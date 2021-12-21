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

import io.renren.modules.generator.entity.UserMemberEntity;
import io.renren.modules.generator.service.UserMemberService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 用户; InnoDB free: 7168 kB
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-12-21 15:04:29
 */
@RestController
@RequestMapping("generator/usermember")
public class UserMemberController {
    @Autowired
    private UserMemberService userMemberService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:usermember:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userMemberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:usermember:info")
    public R info(@PathVariable("id") String id){
		UserMemberEntity userMember = userMemberService.getById(id);

        return R.ok().put("userMember", userMember);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:usermember:save")
    public R save(@RequestBody UserMemberEntity userMember){
		userMemberService.save(userMember);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:usermember:update")
    public R update(@RequestBody UserMemberEntity userMember){
		userMemberService.updateById(userMember);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:usermember:delete")
    public R delete(@RequestBody String[] ids){
		userMemberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
