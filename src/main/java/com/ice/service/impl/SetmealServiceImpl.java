package com.ice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.dto.SetmealDto;
import com.ice.entity.Setmeal;
import com.ice.entity.SetmealDish;
import com.ice.mapper.SetmealMapper;
import com.ice.service.SetmealDishService;
import com.ice.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Title: SetmealServiceImpl
 * @Auth: Ice
 * @Date: 2023/3/26 11:26
 * @Version: 1.0
 * @Desc:
 */

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Resource
    private SetmealDishService setmealDishService;

    @Transactional
    @Override
    public void saveWithDish(SetmealDto setmealDto) {
        save(setmealDto);
        setmealDto.getSetmealDishes()
                .forEach(setmealDish -> {
                    setmealDish.setSetmealId(setmealDto.getId());
                });
        setmealDishService.saveBatch(setmealDto.getSetmealDishes());
    }

    @Override
    public SetmealDto getByIdWithDish(Long id) {
        Setmeal setmeal = getById(id);
        SetmealDto setmealDto = new SetmealDto();
        BeanUtils.copyProperties(setmeal, setmealDto);

        LambdaQueryWrapper<SetmealDish> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SetmealDish::getSetmealId, setmeal.getId());

        setmealDto.setSetmealDishes(setmealDishService.list(wrapper));
        return setmealDto;

    }

    @Override
    public void updateWithDish(SetmealDto setmealDto) {
        //更新setmeal表基本信息
        updateById(setmealDto);

        //更新setmeal_dish表信息delete操作
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDish::getSetmealId, setmealDto.getId());
        setmealDishService.remove(queryWrapper);

        //更新setmeal_dish表信息insert操作
        List<SetmealDish> SetmealDishes = setmealDto.getSetmealDishes();

        SetmealDishes.forEach((item) -> {
            item.setSetmealId(setmealDto.getId());
        });
        setmealDishService.saveBatch(SetmealDishes);
    }

}
