package com.ice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.entity.Employee;
import com.ice.mapper.EmployeeMapper;
import com.ice.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @Title: EmployeeServiceImpl
 * @Auth: Ice
 * @Date: 2023/3/24 9:11
 * @Version: 1.0
 * @Desc:
 */

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}

