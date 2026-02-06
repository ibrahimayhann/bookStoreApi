package com.ibrahimayhan.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDto {
	

	@NotEmpty(message = "Title cannot be empty!")
	private String title;
	
	
	private BigDecimal price;
	
	
	@Size(min = 13,max = 13)
	private String isbn13;
	
	@NotEmpty(message = "Publisher name cannot be emty!")
	private String publisherName;
	
	
	private Integer publishYear;
	
	@NotEmpty(message = "Author name cannot be emty!")
	private String authorNameSurname;
}
