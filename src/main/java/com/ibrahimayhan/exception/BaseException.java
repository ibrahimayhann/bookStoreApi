package com.ibrahimayhan.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	private final ErrorCode errorCode;
	
	
	public BaseException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode=errorCode;
	}
	
	
	public BaseException(ErrorCode errorCode,String detail) {
		super(errorCode.getMessage()+(detail !=null && !detail.isBlank() ? " : " + detail :""));
		this.errorCode=errorCode;
	}
}
