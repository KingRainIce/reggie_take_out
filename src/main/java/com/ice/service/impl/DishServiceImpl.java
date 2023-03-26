package com.ice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.dto.DishDto;
import com.ice.entity.Dish;
import com.ice.entity.DishFlavor;
import com.ice.mapper.DishMapper;
import com.ice.service.DishFlavorService;
import com.ice.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: DishServiceImpl
 * @Auth: Ice
 * @Date: 2023/3/26 11:24
 * @Version: 1.0
 * @Desc:
 */

@Slf4j
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Resource
    private DishFlavorService dishFlavorService;

    @Override
    @Transactional
    public void saveWithFlavor(DishDto dishDto) {

        this.save(dishDto);

        Long dishId = dishDto.getId();

        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors.forEach(flavor -> {
            flavor.setDishId(dishId);
        });

        dishFlavorService.saveBatch(flavors);

    }
}
