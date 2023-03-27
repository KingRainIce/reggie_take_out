package com.ice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ice.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Title: OrderMapper
 * @Auth: Ice
 * @Date: 2023/3/27 19:37
 * @Version: 1.0
 * @Desc:
 */

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
