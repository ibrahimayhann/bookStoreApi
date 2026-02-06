package com.ibrahimayhan.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublisherDetailResponseDto {

	private Long publisherId;
	
	private String publisherName;
	
	private List<BookResponseDto> books;
}
