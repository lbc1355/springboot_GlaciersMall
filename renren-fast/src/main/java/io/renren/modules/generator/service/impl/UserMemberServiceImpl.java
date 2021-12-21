package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.UserMemberDao;
import io.renren.modules.generator.entity.UserMemberEntity;
import io.renren.modules.generator.service.UserMemberService;


@Service("userMemberService")
public class UserMemberServiceImpl extends ServiceImpl<UserMemberDao, UserMemberEntity> implements UserMemberService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserMemberEntity> page = this.page(
                new Query<UserMemberEntity>().getPage(params),
                new QueryWrapper<UserMemberEntity>()
        );

        return new PageUtils(page);
    }

}