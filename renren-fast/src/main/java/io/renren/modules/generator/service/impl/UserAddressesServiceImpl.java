package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.UserAddressesDao;
import io.renren.modules.generator.entity.UserAddressesEntity;
import io.renren.modules.generator.service.UserAddressesService;


@Service("userAddressesService")
public class UserAddressesServiceImpl extends ServiceImpl<UserAddressesDao, UserAddressesEntity> implements UserAddressesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserAddressesEntity> page = this.page(
                new Query<UserAddressesEntity>().getPage(params),
                new QueryWrapper<UserAddressesEntity>()
        );

        return new PageUtils(page);
    }

}