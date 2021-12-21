package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.GoodsSkuDao;
import io.renren.modules.generator.entity.GoodsSkuEntity;
import io.renren.modules.generator.service.GoodsSkuService;


@Service("goodsSkuService")
public class GoodsSkuServiceImpl extends ServiceImpl<GoodsSkuDao, GoodsSkuEntity> implements GoodsSkuService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<GoodsSkuEntity> page = this.page(
                new Query<GoodsSkuEntity>().getPage(params),
                new QueryWrapper<GoodsSkuEntity>()
        );

        return new PageUtils(page);
    }

}