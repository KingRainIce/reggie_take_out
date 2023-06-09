package com.ice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ice.common.R;
import com.ice.dto.DishDto;
import com.ice.entity.Dish;

/**
 * @Title: DishService
 * @Auth: Ice
 * @Date: 2023/3/26 11:24
 * @Version: 1.0
 * @Desc:
 */

public interface DishService extends IService<Dish> {

    public void saveWithFlavor(DishDto dishDto);

    DishDto getByIdWithFlavor(Long id);

    void updateWithFlavor(DishDto dishDto);

    R<String> updateStatus(Integer status, String[] ids);
}
