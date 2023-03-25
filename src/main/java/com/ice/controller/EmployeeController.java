package com.ice.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ice.common.R;
import com.ice.entity.Employee;
import com.ice.service.impl.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Title: EmployeeController
 * @Auth: Ice
 * @Date: 2023/3/24 9:12
 * @Version: 1.0
 * @Desc:
 */

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Resource
    private EmployeeServiceImpl employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request,
                             @RequestBody Employee employee) {
        // The password submitted by the page is encrypted with MD 5
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // Check the database based on the username submitted by the page
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(wrapper);

        // No such user
        if (emp == null) {
            return R.error("用户名不存在");
        }

        // Password error
        if (!emp.getPassword().equals(password)) {
            return R.error("密码错误");
        }

        // User is disabled
        if (emp.getStatus() == 0) {
            return R.error("用户被禁用");
        }

        // Login successful，save user information to session
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }


    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        // Clean up the IDs of current employee logins saved in the Session
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

}
