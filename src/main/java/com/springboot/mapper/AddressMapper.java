package com.springboot.mapper;

import com.springboot.entity.Address;

public interface AddressMapper {
    /**
     * 根据uid统计地址数量
     * @param uid 用户uid
     * @return 地址总量
     */
    Integer countByUid(Integer uid);

    /**
     * 将地址存入数据库
     * @param address 用户信息
     * @return 插入数量
     */
    Integer saveAddress(Address address);
}
