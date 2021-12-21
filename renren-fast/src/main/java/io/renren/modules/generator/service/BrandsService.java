package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.BrandsEntity;

import java.util.Map;

/**
 * 品牌; InnoDB free: 7168 kB
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-12-21 15:04:29
 */
public interface BrandsService extends IService<BrandsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

