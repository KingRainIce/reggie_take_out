package com.ice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ice.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Title: DishMapper
 * @Auth: Ice
 * @Date: 2023/3/26 11:21
 * @Version: 1.0
 * @Desc:
 */

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
