package com.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.controller.ex.FileEmptyException;
import com.springboot.controller.ex.FileIOException;
import com.springboot.controller.ex.FileSizeException;
import com.springboot.controller.ex.FileStateException;
import com.springboot.controller.ex.FileTypeException;
import com.springboot.entity.User;
import com.springboot.service.IUserService;
import com.springboot.util.JsonResult;

@RestController
@RequestMapping("users")
public class UserCotrolelr extends BaseController{
	
	private static final int JsonResult = 0;
	private static final int String = 0;
	@Autowired
	IUserService service;
	/**
	 * 用户注册
	 * @param user 用户数据
	 * @return 对象服务器的响应
	 */
	@RequestMapping("reg")
	public JsonResult<Void> reg(User user){
		//执行注册方法
		service.reg(user);
		//返回json对象
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
		//执行业务层login方法
		User user = service.login(username, password);
		//将uid和Username放入session中
		session.setAttribute("uid",user.getUid());
		session.setAttribute("username", username);
		//将登陆之后的用户数据返回
		json.setData(user);
		json.setState(SUCCESS);
		return json;
	}
	
	/**
	 * 修改信息
	 * @param user  用户输入的信息
	 * @param session 
	 * @return
	 */
	@RequestMapping("change_info")
	public JsonResult<Void> updateInfo(User user,HttpSession session) {
		//从session中获取uid和Username
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		String username = session.getAttribute("username").toString();
		//执行修改信息方法
		service.updateInfo(uid, username, user);
		return new JsonResult<Void>(SUCCESS);
	}
	
	/**
	 * 修改密码
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @param session
	 * @return
	 */
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
	//头像的大小
	private static final Integer AVATAR_SIZE = 1*2014*2014;
	private static final  List<String> AVATAR_TYPE = new ArrayList<String>();
	static{
		AVATAR_TYPE.add("image/jept");
		AVATAR_TYPE.add("image/png");
	}
	@RequestMapping("change_avatar")
	public JsonResult<String> changeAvatar(HttpServletRequest request,
			@RequestParam("file")MultipartFile file, HttpSession session){
		//判断文件是否为空
		if(file.isEmpty()) {
			throw new FileEmptyException("文件不能为空");
		}
		//判断文件类型是否正确
		if(!AVATAR_TYPE.contains(file.getContentType())) {
			throw new FileTypeException("文件类型不匹配");
		}
		//判断文件大小
		if(AVATAR_SIZE<file.getSize()) {
			throw new FileSizeException("文件过大，不能超过"+AVATAR_SIZE/1024+"kb");
		}
		//先获取文件名
		String oFileName = file.getOriginalFilename();
		//获取上传文件的后缀
		int index = oFileName.lastIndexOf(".");
		String suffix = "";
		//判断是否为-1
		if(index!=-1) {
			suffix = oFileName.substring(index);
		}
		//生成一个随机的唯一数
		String filename = UUID.randomUUID().toString()+suffix;
		//获取当前真实路径
		String filePath = request.getServletContext().getRealPath("upload");
		//判断是否有upload文件夹
		File parent = new File(filePath);
		if(!parent.exists()) {
			parent.mkdir();
		}
		
		File dest = new File(filePath,filename);
		//将用户头像上传到服务器
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			throw new FileStateException("文件上传异常"+e.getMessage());
		} catch (IOException e) {
			throw new FileIOException("文件上传异常"+e.getMessage());
		}
		//将相对路径存入数据库
		String avatar = "/upload/"+filename;
		//获取uid和Username
		Integer uid = getUidFromSession(session);
		String modifiedUser=getUsernameFromSession(session);
		service.changeAvatar(avatar, uid, modifiedUser);
		return new JsonResult<String>(SUCCESS,avatar);
	}
	
	/**
	 * 根据id查询数据
	 * @param session
	 * @return
	 */
	@RequestMapping("query_by_id")
	public JsonResult<User> queryById(HttpSession session){
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		User user = service.queryById(uid);
		return new JsonResult<User>(SUCCESS,user);
	}
	
	
}
