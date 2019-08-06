package com.springboot.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.entity.User;
import com.springboot.mapper.UserMapper;
import com.springboot.service.IUserService;
import com.springboot.util.JsonResult;

@RestController
@RequestMapping("users")
public class UserCotrolelr extends BaseController{
	
	@Autowired
	IUserService service;
	/**
	 * 用户注册
	 * @param user 用户数据
	 * @return 对象服务器的响应
	 */
	@RequestMapping("reg")
	public JsonResult<Void> reg(User user){
		service.reg(user);
		return new JsonResult<Void>(SUCCESS);
	}
	/**
	 * 用户登陆
	 * @param username 用户名
	 * @param password 密码
	 * @param session 用来储存用户登陆的数据
	 * @return 对象服务器的响应
	 */
	@RequestMapping("login")
	public JsonResult<User> login(String username,String password,HttpSession session){
		JsonResult<User> json = new JsonResult<User>();
		User user = service.login(username, password);
		session.setAttribute("uid",user.getUid());
		session.setAttribute("username", username);
		json.setData(user);
		json.setState(SUCCESS);
		return json;
	}
	
	@RequestMapping("change_password")
	public JsonResult<Void> changePssword(@RequestParam("old_password")String oldPassword,
			@RequestParam("new_password")String newPassword,
			HttpSession session){
		JsonResult<Void> json = new JsonResult<Void>();
		String username = session.getAttribute("username").toString();
		service.changePssword(oldPassword, newPassword, username, new Date());
		json.setState(SUCCESS);
		return json;
	}
	
	@RequestMapping("query_by_id")
	public JsonResult<User> queryById(HttpSession session){
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		User user = service.queryById(uid);
		return new JsonResult<User>(SUCCESS,user);
	}
	
	@RequestMapping("change_info")
	public JsonResult<Void> updateInfo(User user,HttpSession session) {
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		String username = session.getAttribute("username").toString();
		service.updateInfo(uid, username, user);
		return new JsonResult<Void>(SUCCESS);
	}
}
