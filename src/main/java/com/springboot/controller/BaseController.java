package com.springboot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.springboot.service.ex.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springboot.controller.ex.FileEmptyException;
import com.springboot.controller.ex.FileIOException;
import com.springboot.controller.ex.FileSizeException;
import com.springboot.controller.ex.FileStateException;
import com.springboot.controller.ex.FileTypeException;
import com.springboot.controller.ex.FileUploadException;
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
			json.setState(30);
		}else if(e instanceof AddressCountLimitException) {
			json.setState(30);
		}else if(e instanceof UpdateException) {
			json.setState(30);
		}else if(e instanceof PasswordNotMatchException) {
			json.setState(30);
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
	
	public Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
	}
	public String getUsernameFromSession(HttpSession session) {
		return session.getAttribute("username").toString();
	}
}
