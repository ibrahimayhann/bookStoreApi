package com.ibrahimayhan.controller.impl;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibrahimayhan.controller.IBookController;
import com.ibrahimayhan.dto.BookRequestDto;
import com.ibrahimayhan.dto.BookResponseDto;
import com.ibrahimayhan.service.IBookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/api/book")
@RequiredArgsConstructor
public class BookControllerImpl implements IBookController{

	private final IBookService bookService;

	@GetMapping("/list")
	@Override
	public List<BookResponseDto> getAllBooks() {
		return bookService.getAllBooks();
	}

	
	@PostMapping("/save")
	@Override
	public BookResponseDto saveBook(@RequestBody @Valid BookRequestDto request) {
		return bookService.saveBook(request);
	}
	
	@GetMapping("/starts-with")
	@Override
	public List<BookResponseDto> getBooksStartWith(@RequestParam String prefix) {
		return bookService.getBooksStartWith(prefix);
	}

	@GetMapping("/published-after")
	@Override
	public List<BookResponseDto> getBooksPublishedAfter(@RequestParam int year) {
		return bookService.getBooksPublishedAfter(year);
	}


	@PutMapping("/update/{id}")
	@Override
	public BookResponseDto updateBook(@PathVariable Long id, @RequestBody BookRequestDto requestDto) {
		return bookService.updateBook(id, requestDto);
	}


	@DeleteMapping("/delete/{id}")
	@Override
	public void deleteBook(@PathVariable Long id) {

		 bookService.deleteBook(id);
	}
}
