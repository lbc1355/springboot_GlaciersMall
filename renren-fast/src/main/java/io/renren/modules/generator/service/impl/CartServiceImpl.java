package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.CartDao;
import io.renren.modules.generator.entity.CartEntity;
import io.renren.modules.generator.service.CartService;


@Service("cartService")
public class CartServiceImpl extends ServiceImpl<CartDao, CartEntity> implements CartService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CartEntity> page = this.page(
                new Query<CartEntity>().getPage(params),
                new QueryWrapper<CartEntity>()
        );

        return new PageUtils(page);
    }

}