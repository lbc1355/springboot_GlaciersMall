package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.BrandsDao;
import io.renren.modules.generator.entity.BrandsEntity;
import io.renren.modules.generator.service.BrandsService;


@Service("brandsService")
public class BrandsServiceImpl extends ServiceImpl<BrandsDao, BrandsEntity> implements BrandsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BrandsEntity> page = this.page(
                new Query<BrandsEntity>().getPage(params),
                new QueryWrapper<BrandsEntity>()
        );

        return new PageUtils(page);
    }

}