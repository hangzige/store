package com.springboot.mapper;

import com.springboot.entity.Address;

import java.util.List;

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

    /**
     * 用uid查找用户有多少地址
     * @param uid
     * @return
     */
    List<Address> findByUid(Integer uid);
}
