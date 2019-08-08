package com.springboot.service.impl;

import com.springboot.entity.Address;
import com.springboot.mapper.AddressMapper;
import com.springboot.service.IAddressService;
import com.springboot.service.ex.AddressCountLimitException;
import com.springboot.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    AddressMapper mapper;
    private  Integer countByUid(Integer uid){
        if(uid==null||uid<1){
            throw new IllegalArgumentException("用户信息找不到");
        }
       return mapper.countByUid(uid);
    }
    private void saveAddress(Address address){
        Integer row = mapper.saveAddress(address);
        if(row!=1){
            throw new InsertException("插入失败：请联系管理员");
        }
    }
    @Override
    public void createAddress(Integer uid, String username, Address address) throws AddressCountLimitException, InsertException {
        //获取用户存了几条地址
        Integer count = countByUid(uid);
        //判断地址不能超过3条
        if(count>=COUNT_MAX){
            throw new AddressCountLimitException("地址超过上限，不能超过"+COUNT_MAX);
        }
        //补全uid
        address.setUid(uid);
        // 补全isDefault，根据上面查询到的收货地址条数进行判断
        int isDefault=count==0 ? 1 : 0;
        address.setIsDefault(isDefault);

        Date now = new Date();
        address.setCreatedTime(now);
        address.setCreatedUser(username);
        address.setModifiedTime(now);
        address.setCreatedUser(username);
        saveAddress(address);

    }
}
