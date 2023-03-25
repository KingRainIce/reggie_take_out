package com.ice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ice.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Title: EmployeeMapper
 * @Auth: Ice
 * @Date: 2023/3/24 9:09
 * @Version: 1.0
 * @Desc:
 */

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
