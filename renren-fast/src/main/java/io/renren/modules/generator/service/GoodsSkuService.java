package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.GoodsSkuEntity;

import java.util.Map;

/**
 * SKU; InnoDB free: 7168 kB
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-12-21 15:04:29
 */
public interface GoodsSkuService extends IService<GoodsSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

