package com.ice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.entity.ShoppingCart;
import com.ice.mapper.ShoppingCartMapper;
import com.ice.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * @Title: ShoppinCartService
 * @Auth: Ice
 * @Date: 2023/3/27 19:21
 * @Version: 1.0
 * @Desc:
 */

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
