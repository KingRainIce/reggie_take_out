package com.ice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.entity.Category;
import com.ice.mapper.CategoryMapper;
import com.ice.service.CategoryService;
import org.springframework.stereotype.Service;

/**
 * @Title: CategoryServiceImpl
 * @Auth: Ice
 * @Date: 2023/3/26 10:25
 * @Version: 1.0
 * @Desc:
 */

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
