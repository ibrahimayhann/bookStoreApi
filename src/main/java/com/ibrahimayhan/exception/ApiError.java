package com.ibrahimayhan.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError<T> {

	private String id;
	
	private Date errorTime;
	
	private String code;   
	
	private String path;
	
	private T errors;
}
//exception handlerda kullanıcıya belirli formatta response dönerken bu generic sınıfın nesnesini oluşturup response ettim