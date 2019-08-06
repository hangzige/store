package com.springboot.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class JsonResult<T> {
	@JsonInclude(value=Include.ALWAYS)
	private Integer state;
	private String message;
	private T data;

	public JsonResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public JsonResult(String message) {
		super();
		this.message = message;
	}

	public JsonResult(Integer state) {
		super();
		this.state = state;
	}
	public JsonResult(Integer state,T date) {
		super();
		this.state = state;
		this.data = date;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
