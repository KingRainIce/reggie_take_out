package com.ice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ice.dto.SetmealDto;
import com.ice.entity.Setmeal;

/**
 * @Title: SetmealService
 * @Auth: Ice
 * @Date: 2023/3/26 11:25
 * @Version: 1.0
 * @Desc:
 */

public interface SetmealService extends IService<Setmeal> {

    void saveWithDish(SetmealDto setmealDto);

    SetmealDto getByIdWithDish(Long id);

    void updateWithDish(SetmealDto setmealDto);
}
