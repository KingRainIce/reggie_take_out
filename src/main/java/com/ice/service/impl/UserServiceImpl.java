package com.ice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.entity.User;
import com.ice.mapper.UserMapper;
import com.ice.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Title: UserServiceImpl
 * @Auth: Ice
 * @Date: 2023/3/27 11:14
 * @Version: 1.0
 * @Desc:
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
