package com.ibrahimayhan.feign;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class BookSearchApiResponseDto {


	private String title;
	
	private BigDecimal price;
	
	private String ISBN13;
	
	private String publisherName;
	
	private String authorNameSurname;

}