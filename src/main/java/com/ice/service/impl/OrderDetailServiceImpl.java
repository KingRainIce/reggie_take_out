package com.ice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.entity.OrderDetail;
import com.ice.mapper.OrderDetailMapper;
import com.ice.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * @Title: OrderDetailServicelmpl
 * @Auth: Ice
 * @Date: 2023/3/27 19:49
 * @Version: 1.0
 * @Desc:
 */

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
