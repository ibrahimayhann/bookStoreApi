/*package com.ibrahimayhan.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;

import com.ibrahimayhan.dto.BookResponseDto;
import com.ibrahimayhan.dto.PublisherWithBooksResponseDto;

public class deneme {

	List<Long> ids = publisherRepository.findTop2PublisherIds(PageRequest.of(0, 2));

	
	List<Publisher> publisherListFromDb=publisherRepository.findPublishersWithBooksAndAuthorsByIds(ids);
	 
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
*/