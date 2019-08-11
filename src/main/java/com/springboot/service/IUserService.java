package com.springboot.service;

import java.util.Date;

import com.springboot.entity.User;
import com.springboot.service.ex.InsertException;
import com.springboot.service.ex.PasswordNotMatchException;
import com.springboot.service.ex.UpdateException;
import com.springboot.service.ex.UserNotFoundException;
import com.springboot.service.ex.UsernameDuplicateException;

public interface IUserService {
	/**
	 * 用户注册
	 * @param user 注册用户的数据
	 * @throws InsertException 插入失败抛出的异常
	 * @throws UsernameDuplicateException 用户名和数据库有相同抛出的异常
	 */
	void reg(User user) throws InsertException,UsernameDuplicateException;
	
	/**
	 * 用户登陆
	 * @param username 用户传入的用户名
	 * @param password 用户传入的密码
	 * @return User 返回已经找到的用户数据
	 * @throws UsernameNotFoundException 与用户输入的用户名找不到抛出的异常
	 * @throws PasswordNotMatchExceptionsword 与用户的密码不一样抛出的异常
	 */
	User login(String username,String password)throws UserNotFoundException,PasswordNotMatchException;
	
	/**
	 * 修改密码
	 * @param oldPassowd 旧密码
	 * @param newPassword 新密码
	 * @throws UsernameNotFoundException 数据库不存在此用户
	 * @throws PasswordNotMatchException 密码不匹配和修改失败
	 */
	void changePssword(String oldPassword,String newPassword,String username,Date modifiedTime)throws UserNotFoundException,PasswordNotMatchException,UpdateException;
	
	/**
	 * 更改头像
	 * @param avatar 用户头像
	 * @param uid 用户id
	 * @param modifiedUser 修改人
	 * @throws UserNotFoundException 用户数据不存在
	 * @throws UpdateException 更新失败
	 */
	void changeAvatar(String avatar,Integer uid,String modifiedUser)throws UserNotFoundException,UpdateException;
	
	/**
	 * 修改用户信息
	 * @param uid 用户id
	 * @param username 用户名
	 * @param user 用户传过来的数据
	 * @return 收影响的行数
	 * @throws UpdateException 修改失败抛出
	 * @throws UserNotFoundException 用户数据找不到抛出
	 */
	void updateInfo(Integer uid,String username,User user)throws UpdateException,UserNotFoundException;
	
	/**
	 * 将用户信息从数据库查找出来
	 * @param uid 用户id
	 * @return 用户数据
	 * @throws UserNotFoundException 数据库没有数据抛出
	 */
	User queryById(Integer uid)throws UserNotFoundException;
	
	
}
