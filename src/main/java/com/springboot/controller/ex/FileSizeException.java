package com.springboot.controller.ex;
/**
 * 文件大小超过上限异常
 *
 */
public class FileSizeException extends FileUploadException {

	public FileSizeException() {
		// TODO Auto-generated constructor stub
	}

	public FileSizeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileSizeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public FileSizeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
