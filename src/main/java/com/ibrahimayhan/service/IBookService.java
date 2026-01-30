package com.ibrahimayhan.service;
import java.util.List;

import com.ibrahimayhan.dto.BookRequestDto;
import com.ibrahimayhan.dto.BookResponseDto;

public interface IBookService {

	public List<BookResponseDto> getAllBooks();
	
	public BookResponseDto saveBook(BookRequestDto request);
	
	public List<BookResponseDto> getBooksStartWith(String prefix);
	
	public List<BookResponseDto> getBooksPublishedAfter(int year);
	
	public BookResponseDto updateBook(Long id,BookRequestDto requestDto);
	
	public void deleteBook(Long id);
	
}
