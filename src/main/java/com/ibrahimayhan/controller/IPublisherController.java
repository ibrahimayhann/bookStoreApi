package com.ibrahimayhan.controller;

import java.util.List;

import com.ibrahimayhan.dto.PublisherResponseDto;
import com.ibrahimayhan.dto.PublisherDetailResponseDto;

public interface IPublisherController {

	public List<PublisherResponseDto> getAllPublisher();
	
	public List<PublisherDetailResponseDto> getTwoPublishersWithBooksAndAuthors();
}
