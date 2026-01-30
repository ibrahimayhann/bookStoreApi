package com.ibrahimayhan.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublisherRequestDto {
	

	@NotEmpty(message = "PublisherName cannot be empty!")
	private String publisherName;
}
