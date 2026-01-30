package com.ibrahimayhan.controller.impl;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibrahimayhan.feign.BookSearchApiResponseDto;
import com.ibrahimayhan.service.impl.GoogleBooksService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/external/books")
@RequiredArgsConstructor
public class GoogleBooksController {

	private final GoogleBooksService googleBooksService;
	
	@GetMapping("/search")
	public List<BookSearchApiResponseDto> search (@RequestParam String q){
		
		return googleBooksService.searchBooks(q);
	}
}
