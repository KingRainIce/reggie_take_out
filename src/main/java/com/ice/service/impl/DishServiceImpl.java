package com.ice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.entity.Dish;
import com.ice.mapper.DishMapper;
import com.ice.service.DishService;
import org.springframework.stereotype.Service;

/**
 * @Title: DishServiceImpl
 * @Auth: Ice
 * @Date: 2023/3/26 11:24
 * @Version: 1.0
 * @Desc:
 */

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {
}
