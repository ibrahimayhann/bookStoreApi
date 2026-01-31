package com.ibrahimayhan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ibrahimayhan.entities.Book;
import com.ibrahimayhan.entities.Publisher;
import com.ibrahimayhan.repository.PublisherRepository;
import com.ibrahimayhan.service.IPublisherService;
import com.ibrahimayhan.dto.BookResponseDto;
import com.ibrahimayhan.dto.PublisherResponseDto;
import com.ibrahimayhan.dto.PublisherWithBooksResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PublisherServiceImpl implements IPublisherService{

	private final PublisherRepository publisherRepository;
	

	//tüm publisherları db den çekip dto ya çevirip döner
	@Override
	public List<PublisherResponseDto> getAllPublisher() {
		List<Publisher>  publisherList=publisherRepository.findAll();
		
		List<PublisherResponseDto> publisherDtoList=new ArrayList<>();
		
		if(publisherList != null && !publisherList.isEmpty()) {
			
			for (Publisher publisher : publisherList) {
				PublisherResponseDto publisherDto = new PublisherResponseDto();
				BeanUtils.copyProperties(publisher, publisherDto);
				publisherDtoList.add(publisherDto);
			}
		}
		
		return publisherDtoList;
	}


	//yayınevini dönerken tüm bağlı booksları ve o booksların author unu döner 
	//case pdf inde 2 tane istendiği için limiti 2 yaptım istersek hepsini de alabiliriz
	@Override
	public List<PublisherWithBooksResponseDto> getTwoPublishersWithBooksAndAuthors() {
		
		List<Publisher> publisherListFromDb=publisherRepository.findPublisherWithBooksAndAuthors().stream().limit(2).toList();
		
		List<PublisherWithBooksResponseDto> publisherDtoList=new ArrayList<>();
		
		for (Publisher publisher : publisherListFromDb) {

	        PublisherWithBooksResponseDto pDto =new PublisherWithBooksResponseDto();
	        pDto.setPublisherId(publisher.getPublisherId());
	        pDto.setPublisherName(publisher.getPublisherName());

	        List<BookResponseDto> bookDtoList = new ArrayList<>();

	        for (Book book : publisher.getBooks()) {

	            BookResponseDto bDto = new BookResponseDto();
	            BeanUtils.copyProperties(book, bDto);

	            bDto.setAuthorNameSurname(book.getAuthor().getAuthorNameSurname());
	            bDto.setPublisherName(publisher.getPublisherName());

	            bookDtoList.add(bDto);
	        }

	        pDto.setBooks(bookDtoList);
	        publisherDtoList.add(pDto);
	    }

	    return publisherDtoList;
	}
	
	
}
