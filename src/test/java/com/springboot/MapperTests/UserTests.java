package com.springboot.MapperTests;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.entity.User;
import com.springboot.mapper.UserMapper;
import com.springboot.service.impl.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {
	
	@Autowired
	UserMapper mapper;
	@Autowired
	UserServiceImpl us;
	@Test
	public void adddd() {
		User user = new User();
		user.setUsername("孙于川");
		user.setPassword("123456");
		Integer n = mapper.addNew(user);
		System.out.println(n);
	}
	
	@Test
	public void insert() {
		User user = mapper.findByUsername("李航");
		System.out.println(user);
	}
	@Test
	public void inser1() {
		try {
			us.login("李", "12356");
		} catch (Exception e) {
			System.out.println(e.getClass().getName());
			System.out.println(e.getMessage());;
		}
	}
	
	@Test
	public void changePassword() {
		try {
			us.changePssword("1234", "123456", "李航", new Date());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getClass().getName());
		}
	}
	
	@Test
	public void findByUid() {
		User user = mapper.findByUid(5);
		System.err.println(user);
	}
	@Test
	public void changeInfo() {
		User user = new User();
		user.setUid(6);
		user.setGender(1);
		user.setEmail("765143406@qq.com");
		user.setPhone("1231654");
		user.setUsername("卓玉坤");
		user.setModifiedTime(new Date());
		Integer row = mapper.changeInfo(user);
		System.out.println(row);
	}
}
