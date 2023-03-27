package com.ice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ice.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Title: UserMapper
 * @Auth: Ice
 * @Date: 2023/3/27 11:13
 * @Version: 1.0
 * @Desc:
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
