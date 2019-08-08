package com.springboot.MapperTests;

import com.springboot.entity.Address;
import com.springboot.mapper.AddressMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressTests {
    @Autowired
    AddressMapper mapper;
    @Test
    public void addressTest(){
        /*Address address = new Address();
        address.setAddress("重庆");
        address.setUid(1);

        Integer row = mapper.saveAddress(address);
        System.out.println(row);*/
        Integer row = mapper.countByUid(1);
        System.out.println(row);
    }
}
