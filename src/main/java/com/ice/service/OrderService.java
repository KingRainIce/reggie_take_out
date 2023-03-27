package com.ice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ice.entity.Orders;

/**
 * @Title: OrderService
 * @Auth: Ice
 * @Date: 2023/3/27 19:38
 * @Version: 1.0
 * @Desc:
 */

public interface OrderService extends IService<Orders> {
    void submit(Orders orders);
}
