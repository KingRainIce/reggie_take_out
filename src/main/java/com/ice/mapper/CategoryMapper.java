package com.ice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ice.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Title: CategoryMapper
 * @Auth: Ice
 * @Date: 2023/3/26 10:22
 * @Version: 1.0
 * @Desc:
 */

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
