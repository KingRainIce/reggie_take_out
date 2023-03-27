package com.ice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.common.R;
import com.ice.dto.DishDto;
import com.ice.entity.Dish;
import com.ice.entity.DishFlavor;
import com.ice.mapper.DishMapper;
import com.ice.service.DishFlavorService;
import com.ice.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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

    @Override
    public DishDto getByIdWithFlavor(Long id) {
        Dish dish = this.getById(id);

        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish, dishDto);

        LambdaQueryWrapper<DishFlavor> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DishFlavor::getDishId, id);
        List<DishFlavor> list = dishFlavorService.list(wrapper);

        dishDto.setFlavors(list);
        return dishDto;
    }

    @Override
    public void updateWithFlavor(DishDto dishDto) {
        // Update the basic information of the DISH table
        updateById(dishDto);

        // Delete the original flavor information of the DISH_FLAVOR table
        LambdaQueryWrapper<DishFlavor> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DishFlavor::getDishId, dishDto.getId());
        dishFlavorService.remove(wrapper);

        // Insert the new flavor information into the DISH_FLAVOR table
        List<DishFlavor> flavors = dishDto.getFlavors();
        flavors.forEach(flavor -> {
            flavor.setDishId(dishDto.getId());
        });

        dishFlavorService.saveBatch(flavors);

    }

    @Override
    public R<String> updateStatus(Integer status, String[] ids) {
        for (String id : ids) {
            removeById(id);
        }
        return R.success("修改成功");
    }
}
