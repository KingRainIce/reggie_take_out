package com.ice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ice.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Title: ShoppingCartMapper
 * @Auth: Ice
 * @Date: 2023/3/27 19:20
 * @Version: 1.0
 * @Desc:
 */

@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
}
