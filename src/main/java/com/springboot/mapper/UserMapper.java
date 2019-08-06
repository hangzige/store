package com.springboot.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.springboot.entity.User;

public interface UserMapper {
	/**
	  *  用户注册
	 * @param user
	 * @return
	 */
	Integer addNew(User user);
	
	/**
	 * 用户注册的用户名看数据库是否存在
	 * @param username
	 * @return
	 */
	User findByUsername(String username);
	
	/**
	 * 根据用户id查找
	 * @param uid 用户id
	 * @return 用户数据
	 */
	User findByUid(Integer uid);
	
	/**
	 * 修改用户信息
	 * @param user 用户信息
	 * @return 返回被修改的行数
	 */
	Integer changeInfo(User user);
	
	/**
	 * 根据用户uid修改密码
	 * @param uid 用户uid
	 * @param password 用户密码
	 * @param modifiedUser 修改用户
	 * @param modifiedTime 修改时间
	 * @return
	 */
	Integer changePassword(@Param("uid") Integer uid,
						   @Param("password") String password,
						   @Param("modifiedUser") String modifiedUser,
						   @Param("modifiedTime") Date modifiedTime);
}
