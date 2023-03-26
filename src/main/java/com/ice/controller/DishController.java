package com.ice.controller;

import com.ice.common.R;
import com.ice.dto.DishDto;
import com.ice.service.DishFlavorService;
import com.ice.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Title: DishController
 * @Auth: Ice
 * @Date: 2023/3/26 19:10
 * @Version: 1.0
 * @Desc:
 */

@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {

    @Resource
    private DishFlavorService dishFlavorService;

    @Resource
    private DishService dishService;

    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto) {
        log.info(dishDto.toString());
        dishService.saveWithFlavor(dishDto);
        return R.success("新增菜品成功!");
    }

}
