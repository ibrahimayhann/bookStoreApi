package com.ibrahimayhan.service;

import java.util.List;

import com.ibrahimayhan.dto.PublisherResponseDto;
import com.ibrahimayhan.dto.PublisherWithBooksResponseDto;

public interface IPublisherService {

	public List<PublisherResponseDto> getAllPublisher();
	
	public List<PublisherWithBooksResponseDto> getTwoPublishersWithBooksAndAuthors();
}
