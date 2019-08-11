package com.springboot.MapperTests;

import com.springboot.entity.District;
import com.springboot.mapper.DistrictMapepr;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistrictTests {
    @Autowired
    DistrictMapepr mapper;
    @Test
    public void findByParent(){
        List<District> list =  mapper.findByParent("86");
        System.out.println(list);
    }
}
