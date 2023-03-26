package com.ice.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ice.common.R;
import com.ice.entity.Employee;
import com.ice.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

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
    private EmployeeService employeeService;

    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request,
                             @RequestBody Employee employee) {
        // The password submitted by the page is encrypted with MD 5
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        // Check the database based on the username submitted by the page
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getUsername, employee.getUsername());
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
    public R<String> logout(HttpServletRequest request) {
        // Clean up the IDs of current employee logins saved in the Session
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }


    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("新增员工，员工信息：{}", employee.toString());
        // To set the initial password, MD 5 encryption is required
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        Long empId = (Long) request.getSession().getAttribute("employee");

        employeeService.save(employee);

        return R.success("新增员工成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        // Construct a paging constructor
        Page pageInfo = new Page(page, pageSize);
        // Construct the conditional constructor
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        // Add filters
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        // Add sort criteria
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        // Execute the query
        employeeService.page(pageInfo, queryWrapper);

        return R.success(pageInfo);
    }

    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
        log.info(employee.toString());

        Long empId = (Long) request.getSession().getAttribute("employee");
        employeeService.updateById(employee);

        return R.success("员工信息修改成功");
    }

    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable String id){
        Employee emp = employeeService.getById(id);
        if (emp != null){
            return R.success(emp);
        }
        return R.error("没有查询到该用户信息");
    }


}
