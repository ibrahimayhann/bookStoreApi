package com.ibrahimayhan.controller.impl;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibrahimayhan.controller.IPublisherController;
import com.ibrahimayhan.dto.PublisherResponseDto;
import com.ibrahimayhan.dto.PublisherWithBooksResponseDto;
import com.ibrahimayhan.service.IPublisherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/api/publisher")
@RequiredArgsConstructor
public class PublisherControllerImpl implements IPublisherController {

	private final IPublisherService publisherService;

	
	@GetMapping("/list")
	@Override
	public List<PublisherResponseDto> getAllPublisher() {
		return publisherService.getAllPublisher();
	}


    @GetMapping("/with-books-authors")
	@Override
	public List<PublisherWithBooksResponseDto> getTwoPublishersWithBooksAndAuthors() {
    	return publisherService.getTwoPublishersWithBooksAndAuthors();
	}
}
