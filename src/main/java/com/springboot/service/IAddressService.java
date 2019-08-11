package com.springboot.service;

import com.springboot.entity.Address;
import com.springboot.service.ex.AddressCountLimitException;
import com.springboot.service.ex.InsertException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 添加用户地址信息的业务层
 */
public interface IAddressService {
    public static final Integer COUNT_MAX=3;
    /**
     * 添加用户地址信息
     * @param uid 用户uid
     * @param username 用户名字
     * @param address 用户地址信息
     * @throws AddressCountLimitException 用户地址超过上限异常
     * @throws InsertException 插入失败异常
     */
    void createAddress(Integer uid, String username, Address address)throws AddressCountLimitException, InsertException;

    /**
     * 查找用户收货地址
     * @param uid 用户uid
     * @return
     */
    List<Address> listByUid(Integer uid);
}
