package com.ice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.common.CustomException;
import com.ice.entity.Category;
import com.ice.entity.Dish;
import com.ice.entity.Setmeal;
import com.ice.mapper.CategoryMapper;
import com.ice.mapper.DishMapper;
import com.ice.service.CategoryService;
import com.ice.service.DishService;
import com.ice.service.SetmealService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Title: CategoryServiceImpl
 * @Auth: Ice
 * @Date: 2023/3/26 10:25
 * @Version: 1.0
 * @Desc:
 */

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private DishService dishService;

    @Resource
    private SetmealService setmealService;

    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count1 = dishService.count(dishLambdaQueryWrapper);
        if (count1 > 0) {
            throw new CustomException("该分类下有菜品，不能删除");
        }

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count2 = setmealService.count(setmealLambdaQueryWrapper);
        if (count2 > 0) {
            throw new CustomException("该分类下有套餐，不能删除");
        }

        super.removeById(id);
    }
}
