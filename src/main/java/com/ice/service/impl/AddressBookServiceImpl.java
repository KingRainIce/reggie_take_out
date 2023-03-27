package com.ice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ice.entity.AddressBook;
import com.ice.mapper.AddressBookMapper;
import com.ice.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 * @Title: AddressBookServiceImpl
 * @Auth: Ice
 * @Date: 2023/3/27 16:58
 * @Version: 1.0
 * @Desc:
 */

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
