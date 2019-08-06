package com.springboot.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.springboot.entity.User;
import com.springboot.mapper.UserMapper;
import com.springboot.service.IUserService;
import com.springboot.service.ex.InsertException;
import com.springboot.service.ex.PasswordNotMatchException;
import com.springboot.service.ex.UpdateException;
import com.springboot.service.ex.UsernameDuplicateException;
import com.springboot.service.ex.UserNotFoundException;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	UserMapper usermapper;

	@Override
	public void reg(User user) {
		String username = user.getUsername();
		User result = usermapper.findByUsername(username);
		if (result != null) {
			throw new UsernameDuplicateException("用户名已存在");
		}

		String password = user.getPassword();
		String salt = UUID.randomUUID().toString();
		user.setSalt(salt);

		String passwordMD = getMD(password, salt);
		user.setPassword(passwordMD);

		Date now = new Date();
		user.setIsDelete(0);
		user.setCreatedTime(now);
		user.setCreatedUser(username);
		user.setModifiedTime(now);
		user.setModifiedUser(username);
		Integer row = usermapper.addNew(user);
		if (!row.equals(1)) {
			throw new InsertException("注册失败，请联系客服");
		}
	}

	@Override
	public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
		// 将用户输入的用户名去查看数据库是否存在
		User user = usermapper.findByUsername(username);
		// 判断用户是否存在
		if (user == null) {
			// 用户不存在，抛出UsernameNotFoundException
			throw new UsernameDuplicateException("用户名不存在");
		}

		// 获取is_delete
		Integer isDelete = user.getIsDelete();
		// 判断用户是否被删除
		if (isDelete.equals(1)) {
			// 已被删除,抛出异常PasswordNotMatchException
			throw new PasswordNotMatchException("用户数据不存在");
		}

		// 获取盐值
		String salt = user.getSalt();
		// 将用户输入的密码进行加密处理
		String userPassword = getMD(password, salt);
		String dataPassword = user.getPassword();
		// 将用户输入的密码和数据库的密码进行判断
		if (!userPassword.equals(dataPassword)) {
			// 匹配不成功抛出PasswordNotMatchException
			throw new PasswordNotMatchException("密码错误");
		}

		user.setPassword(null);
		user.setSalt(null);
		user.setIsDelete(null);

		return user;
	}

	/**
	 * 修改密码
	 */
	@Override
	public void changePssword(String oldPassword, String newPassword, String username, Date modifiedTime)
			throws UserNotFoundException, PasswordNotMatchException, UpdateException {
		// 用需要修改的用户名匹配数据库是否存在
		User user = usermapper.findByUsername(username);
		// 判断返回是否为空
		if (user == null) {
			// 如果为空抛出UsernameNotFoundException
			throw new UserNotFoundException("修改错误：用户名不存在");
		}

		// 判断is_delete是否为1
		if (user.getIsDelete().equals(1)) {
			// 是 抛出UsernameNotFoundException
			throw new UserNotFoundException("修改错误：用户数据不存在");
		}

		// 获取盐值
		String salt = user.getSalt();
		// 将旧密码进行加密
		String oldPasswordMd5 = getMD(oldPassword, salt);
		// 判断旧密码和数据库的密码进行对比
		if (!user.getPassword().equals(oldPasswordMd5)) {
			// 不对就抛出PasswordNotMatchException
			throw new PasswordNotMatchException("修改错误：密码不匹配");
		}

		String newPasswordMd5 = getMD(newPassword, salt);
		Integer uid = user.getUid();
		Integer row = usermapper.changePassword(uid, newPasswordMd5, username, modifiedTime);
		if (row != 1) {
			throw new UpdateException("修改错误：请联系系统管理员");
		}

	}

	/**
	 * 查找用户数据
	 */
	@Override
	public User queryById(Integer uid) throws UserNotFoundException {
		// 根据id查找数据库里面是否存在
		User result = usermapper.findByUid(uid);
		// 判断是否为空
		if (result == null) {
			// 为空抛出UserNotFoundException
			throw new UserNotFoundException("查询异常：用户数据找不到");
		}
		// 判断is_delete是否为1
		if (result.getIsDelete().equals(1)) {
			throw new UsernameDuplicateException("查询异常：用户数据不存在");
		}
		// 创建一个空的User对象
		User user = new User();
		user.setUsername(result.getUsername());
		user.setEmail(result.getEmail());
		user.setPhone(result.getPhone());
		user.setGender(result.getGender());
		return user;
	}

	/**
	 * 修改用户信息
	 */
	@Override
	public void updateInfo(Integer uid, String username, User user) throws UpdateException, UserNotFoundException {
		// 根据id查找数据库里面是否存在
		User result = usermapper.findByUid(uid);
		// 判断是否为空
		if (result == null) {
			// 为空抛出UserNotFoundException
			throw new UserNotFoundException("查询异常：用户数据找不到");
		}
		// 判断is_delete是否为1
		if (result.getIsDelete().equals(1)) {
			throw new UsernameDuplicateException("查询异常：用户数据不存在");
		}
		//设置uid
		user.setUid(uid);
		//设置Username
		user.setModifiedUser(username);
		//设置修改时间
		user.setModifiedTime(new Date());
		//修改信息
		Integer row = usermapper.changeInfo(user);
		//判断修改的行数
		if(!row.equals(1)) {
			//不为1修改失败
			throw new UpdateException("更新失败:请联系管理员");
		}
	}

	public String getMD(String password, String salt) {
		password = salt + password + salt;
		for (int i = 0; i < 6; i++) {
			password = DigestUtils.md5DigestAsHex(password.getBytes());
		}
		return password;
	}

}
