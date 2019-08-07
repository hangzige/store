package com.springboot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springboot.controller.ex.FileEmptyException;
import com.springboot.controller.ex.FileIOException;
import com.springboot.controller.ex.FileSizeException;
import com.springboot.controller.ex.FileStateException;
import com.springboot.controller.ex.FileTypeException;
import com.springboot.controller.ex.FileUploadException;
import com.springboot.service.ex.InsertException;
import com.springboot.service.ex.PasswordNotMatchException;
import com.springboot.service.ex.ServiceException;
import com.springboot.service.ex.UserNotFoundException;
import com.springboot.service.ex.UsernameDuplicateException;
import com.springboot.util.JsonResult;
/**
 * 封装了所有的异常信息
 * @author lihang
 *
 */
public abstract class BaseController {
	
	protected static final Integer SUCCESS = 20;
	
	@ExceptionHandler({ServiceException.class,FileUploadException.class})
	public JsonResult<Void> getException(Throwable e) {
		JsonResult<Void> json = new JsonResult<Void>(e.getMessage());
		if(e instanceof UsernameDuplicateException) {
			json.setState(30);
		}else if(e instanceof InsertException) {
			json.setState(30);
		}else if(e instanceof UserNotFoundException) {
			json.setState(40);
		}else if(e instanceof PasswordNotMatchException) {
			json.setState(41);
		}else if(e instanceof FileEmptyException) {
			json.setState(50);
		}else if(e instanceof FileIOException) {
			json.setState(51);
		}else if(e instanceof FileSizeException) {
			json.setState(52);
		}else if(e instanceof FileStateException) {
			json.setState(53);
		}else if(e instanceof FileTypeException) {
			json.setState(54);
		}
		
		return json;
	}
	
	public Integer getUidFromSession(HttpServletRequest request) {
		return Integer.valueOf(request.getSession().getAttribute("uid").toString());
	}
	public String getUsernameFromSession(HttpServletRequest request) {
		return request.getSession().getAttribute("username").toString();
	}
}
