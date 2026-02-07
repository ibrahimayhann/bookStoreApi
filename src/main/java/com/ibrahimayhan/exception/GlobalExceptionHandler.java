package com.ibrahimayhan.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	//validation exceptions
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError<?>> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex ,HttpServletRequest request) {
		
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
		return ResponseEntity.badRequest().body(creaApiError(errorsMap,ErrorCode.VALIDATION_ERROR,request));
	}
	
	
	
	//custom BaseException
	@ExceptionHandler(BaseException.class)
	public ResponseEntity<ApiError<?>> handleBaseException(BaseException ex,HttpServletRequest request){
		
		ErrorCode code=ex.getErrorCode();//createapierror değişkenine vercez
		return ResponseEntity.status(code.getHttpStatus()).body(creaApiError(ex.getMessage(), code, request));
	}
	
	
	
	//Db constraint validation
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiError<?>> handleDataIntegrity(DataIntegrityViolationException ex,HttpServletRequest request){
		
		ErrorCode code=ErrorCode.DUPLICATE_RECORD;
		return ResponseEntity.status(code.getHttpStatus()).body(creaApiError(code.getMessage(), code, request));
	}
	
	
	
	//FallBack - tüm hatalar
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError<?>> handleAll(Exception ex ,HttpServletRequest request){
		
		ErrorCode code=ErrorCode.GENERAL_EXCEPTION;
		return ResponseEntity.status(code.getHttpStatus()).body(creaApiError(code.getMessage(), code, request));
	}
	
	
	
	
	
	
	//Helper method
	//kullanıcıya belirli formatta error response edebilmek için yardımcı fonksiyon
	//Generic yapıda olduğu için hata hashmap de olsa string de olsa sorunsuz çalışır
	private <T> ApiError<T> creaApiError(T errors,ErrorCode errorCode,HttpServletRequest request) {
		ApiError<T> apiError=new ApiError<T>();
		apiError.setId(UUID.randomUUID().toString());
		apiError.setErrorTime(new Date());
		apiError.setCode(errorCode.getCode());
		apiError.setPath(request.getRequestURI());
		apiError.setErrors(errors);
		return apiError;
	}
}
