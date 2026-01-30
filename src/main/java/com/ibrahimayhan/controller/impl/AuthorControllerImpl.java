package com.ibrahimayhan.controller.impl;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibrahimayhan.controller.IAuthorController;
import com.ibrahimayhan.dto.AuthorResponseDto;
import com.ibrahimayhan.service.IAuthorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/api/author")
@RequiredArgsConstructor
public class AuthorControllerImpl implements IAuthorController {
	
	private final IAuthorService authorService;

	@GetMapping("/list")
	@Override
	public List<AuthorResponseDto> getAllAuthors() {
		return authorService.getAllAuthors();
	}

}
