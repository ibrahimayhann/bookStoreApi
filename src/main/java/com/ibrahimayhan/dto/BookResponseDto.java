package com.ibrahimayhan.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponseDto {
	
	private Long bookId;
	
	
	private String title;
	
	
	private BigDecimal price;
	
	
	private String isbn13;
	
	
	private String publisherName;
	
	
	private int publishYear;
	
	
	private String authorNameSurname;
}
