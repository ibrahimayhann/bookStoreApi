package com.ibrahimayhan.service.impl;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ibrahimayhan.entities.Publisher;
import com.ibrahimayhan.mapper.PublisherMapper;
import com.ibrahimayhan.repository.PublisherRepository;
import com.ibrahimayhan.service.IPublisherService;
import com.ibrahimayhan.dto.PublisherDetailResponseDto;
import com.ibrahimayhan.dto.PublisherResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PublisherServiceImpl implements IPublisherService{

	private final PublisherRepository publisherRepository;
	
	private final PublisherMapper publisherMapper;
	

	//tüm publisherları db den çekip dto ya çevirip döner
	@Override
	public List<PublisherResponseDto> getAllPublisher() {
		
		List<Publisher>  publisherList=publisherRepository.findAll();		
		if(publisherList == null || publisherList.isEmpty()) {
			return List.of();
		}
		return publisherMapper.toResponseDtoList(publisherList);
	}


	//yayınevini dönerken tüm bağlı booksları ve o booksların author unu döner 
	@Override
	public List<PublisherDetailResponseDto> getTwoPublishersWithBooksAndAuthors() {
		
		List<Long> ids = publisherRepository.findTop2PublisherIds(PageRequest.of(0, 2));

		if(ids==null||ids.isEmpty()) {
			return List.of();
		}
		List<Publisher> publisherListFromDb=publisherRepository.findPublishersWithBooksAndAuthorsByIds(ids);
		 return publisherMapper.toDetailResponseDtoList(publisherListFromDb);
	}
	
	
}
