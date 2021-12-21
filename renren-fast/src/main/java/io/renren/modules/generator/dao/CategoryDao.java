package io.renren.modules.generator.dao;

import io.renren.modules.generator.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分类; InnoDB free: 7168 kB
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-12-21 15:04:29
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
