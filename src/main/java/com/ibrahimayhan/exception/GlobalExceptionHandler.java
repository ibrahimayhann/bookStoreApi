package com.ibrahimayhan.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	//Burada sadece validasyon hatalarını handle edip kullanıcıya belirli bir formatta response dönüyorum 

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		
		
		//field ve ilgili fileda ait hata listesini toplayacağım hashmap
		Map<String, List<String>> errorsMap = new HashMap<>();

		
		for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
			if(objectError instanceof FieldError fieldError) {
				//object error field hatasıysa buraya gir
				String fieldName=fieldError.getField();
				String errorMessage=fieldError.getDefaultMessage();
				
				//hashmapte daha önce aynı  field adıyla nesne varsa o fieldin mapindeki hata listesine hatayı ekle
				//eğer aynı field adıyla hata yoksa yeni field adıyla hatayı oluşturup onun errorlistine ekle hatayı
				errorsMap.computeIfAbsent(fieldName,k->new ArrayList<>()).add(errorMessage);
			}else {
				//objerror field hatası değil 
				String errorMessage=objectError.getDefaultMessage();
				errorsMap.computeIfAbsent("Global",k->new ArrayList<>()).add(errorMessage);
			}
		}
		
		return ResponseEntity.badRequest().body(creaApiError(errorsMap));

	}
	
	//kullanıcıya belirli formatta error response edebilmek için yardımcı fonksiyon
	//Generic yapıda olduğu için hata hashmap de olsa string de olsa sorunsuz çalışır
	private <T> ApiError<T> creaApiError(T errors) {
		ApiError<T> apiError=new ApiError<T>();
		apiError.setId(UUID.randomUUID().toString());
		apiError.setErrorTime(new Date());
		apiError.setErrors(errors);
		return apiError;
	}
}
