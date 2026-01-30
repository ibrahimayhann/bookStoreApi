package com.ibrahimayhan.controller;

import java.util.List;

import com.ibrahimayhan.dto.PublisherResponseDto;
import com.ibrahimayhan.dto.PublisherWithBooksResponseDto;

public interface IPublisherController {

	public List<PublisherResponseDto> getAllPublisher();
	
	public List<PublisherWithBooksResponseDto> getTwoPublishersWithBooksAndAuthors();
}
