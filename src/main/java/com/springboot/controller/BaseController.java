package com.springboot.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springboot.service.ex.InsertException;
import com.springboot.service.ex.PasswordNotMatchException;
import com.springboot.service.ex.ServiceException;
import com.springboot.service.ex.UsernameDuplicateException;
import com.springboot.service.ex.UserNotFoundException;
import com.springboot.util.JsonResult;
/**
 * 封装了所有的异常信息
 * @author lihang
 *
 */
public abstract class BaseController {
	
	protected static final Integer SUCCESS = 20;
	
	@ExceptionHandler(ServiceException.class)
	public JsonResult<Void> getException(Throwable e) {
		JsonResult<Void> json = new JsonResult<Void>(e.getMessage());
		if(e instanceof UsernameDuplicateException) {
			json.setState(30);
		}else if(e instanceof InsertException) {
			json.setState(30);
		}else if(e instanceof UserNotFoundException) {
			json.setState(30);
		}else if(e instanceof PasswordNotMatchException) {
			json.setState(30);
		}
		return json;
	}
}
