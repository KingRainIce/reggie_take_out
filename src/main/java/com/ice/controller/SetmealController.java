package com.ice.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ice.common.R;
import com.ice.dto.SetmealDto;
import com.ice.entity.Category;
import com.ice.entity.Setmeal;
import com.ice.service.CategoryService;
import com.ice.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Title: SetmealController
 * @Auth: Ice
 * @Date: 2023/3/27 8:26
 * @Version: 1.0
 * @Desc:
 */

@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {

    @Resource
    private SetmealService setmealService;

    @Resource
    private CategoryService categoryService;

    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto) {
        setmealService.saveWithDish(setmealDto);
        return R.success("新增套餐成功!");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        // Query the list of setmeals
        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        Page<SetmealDto> dtoPageInfo = new Page<>();
        LambdaQueryWrapper<Setmeal> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.isNotEmpty(name), Setmeal::getName, name)
                .orderByDesc(Setmeal::getUpdateTime);
        setmealService.page(pageInfo, wrapper);

        BeanUtils.copyProperties(pageInfo, dtoPageInfo, "records");

        List<SetmealDto> list = pageInfo.getRecords().stream()
                .map(item -> {
                    SetmealDto dto = new SetmealDto();
                    BeanUtils.copyProperties(item, dto);

                    Long categoryId = item.getCategoryId();
                    Category category = categoryService.getById(categoryId);

                    if (category != null) {
                        dto.setCategoryName(category.getName());
                    }

                    return dto;
                }).collect(Collectors.toList());

        dtoPageInfo.setRecords(list);

        return R.success(pageInfo);

    }

    @DeleteMapping
    public R<String> delete(String[] ids) {
        int index = 0;
        for (String id : ids) {
            Setmeal setmeal = setmealService.getById(id);
            if (setmeal.getStatus() != 1) {
                setmealService.removeById(id);
            } else {
                index++;
            }
        }
        if (index > 0 && index == ids.length) {
            return R.error("选中的套餐均为启售状态，不能删除");
        } else {
            return R.success("删除成功");
        }
    }

    @PostMapping("/status/{status}")
    public R<String> sale(@PathVariable int status, String[] ids) {
        for (String id : ids) {
            Setmeal setmeal = setmealService.getById(id);
            setmeal.setStatus(status);
            setmealService.updateById(setmeal);
        }
        return R.success("修改成功");
    }

    //根据Id查询套餐信息
    @GetMapping("/{id}")
    public R<SetmealDto> getById(@PathVariable Long id) {
        SetmealDto setmealDto = setmealService.getByIdWithDish(id);
        return R.success(setmealDto);
    }

    //修改套餐
    @PutMapping
    public R<String> update(@RequestBody SetmealDto setmealDto) {
        setmealService.updateWithDish(setmealDto);
        return R.success("修改成功");
    }


}
