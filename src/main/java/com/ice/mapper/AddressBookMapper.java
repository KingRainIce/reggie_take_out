package com.ice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ice.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Title: AddressBookMapper
 * @Auth: Ice
 * @Date: 2023/3/27 16:55
 * @Version: 1.0
 * @Desc:
 */

@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
