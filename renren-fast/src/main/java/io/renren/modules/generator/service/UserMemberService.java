package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.UserMemberEntity;

import java.util.Map;

/**
 * 用户; InnoDB free: 7168 kB
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-12-21 15:04:29
 */
public interface UserMemberService extends IService<UserMemberEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

