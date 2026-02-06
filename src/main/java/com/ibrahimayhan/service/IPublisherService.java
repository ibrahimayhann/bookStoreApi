package com.ibrahimayhan.service;

import java.util.List;

import com.ibrahimayhan.dto.PublisherResponseDto;
import com.ibrahimayhan.dto.PublisherDetailResponseDto;

public interface IPublisherService {

	public List<PublisherResponseDto> getAllPublisher();
	
	public List<PublisherDetailResponseDto> getTwoPublishersWithBooksAndAuthors();
}
