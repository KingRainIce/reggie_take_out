package com.ice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ice.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Title: OrderDetailMapper
 * @Auth: Ice
 * @Date: 2023/3/27 19:38
 * @Version: 1.0
 * @Desc:
 */

@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {
}
