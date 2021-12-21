package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.CityEntity;

import java.util.Map;

/**
 * 城市; InnoDB free: 7168 kB
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-12-21 15:04:29
 */
public interface CityService extends IService<CityEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

