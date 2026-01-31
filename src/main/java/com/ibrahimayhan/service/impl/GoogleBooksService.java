package com.ibrahimayhan.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ibrahimayhan.feign.BookSearchApiResponseDto;
import com.ibrahimayhan.feign.GoogleBooksClient;
import com.ibrahimayhan.feign.GoogleBooksResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GoogleBooksService {

	private final GoogleBooksClient googleBooksClient;
	
	
	
	public List<BookSearchApiResponseDto> searchBooks(String keyword){
		
		GoogleBooksResponseDto response=googleBooksClient.searchBooks(keyword);
		
		if(response.getItems()==null) { 
			return List.of(); }
		
		return response.getItems().stream().map(this::mapToResponseDto).toList();
	}
	
	
	
	//Dto dönüşümü için yardımcı metot
	private BookSearchApiResponseDto mapToResponseDto(GoogleBooksResponseDto.Item item) {
		
		BookSearchApiResponseDto dto=new BookSearchApiResponseDto();
		var volume=item.getVolumeInfo();
		
		dto.setTitle(volume.getTitle());
		dto.setPublisherName(volume.getPublisher());
		
		//Author dto dönüşümü
		if(volume.getAuthors() !=null && ! volume.getAuthors().isEmpty()) {
			dto.setAuthorNameSurname(volume.getAuthors().get(0));
		}
		
		//ISBN13 dto dönüşümü
		if (volume.getIndustryIdentifiers() != null) {
            volume.getIndustryIdentifiers().stream()
                    .filter(i -> "ISBN_13".equals(i.getType()))
                    .findFirst()
                    .ifPresent(i -> dto.setISBN13(i.getIdentifier()));
        }
		
		//Price dto dönüşümü
		if(item.getSaleInfo() != null &&item.getSaleInfo().getRetailPrice()!=null) {
			dto.setPrice(BigDecimal.valueOf(item.getSaleInfo().getRetailPrice().getAmount()));
		}
		
		
		return dto;
	}
	
	
	
	
}
