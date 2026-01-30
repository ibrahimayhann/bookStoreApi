package com.ibrahimayhan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ibrahimayhan.dto.AuthorResponseDto;
import com.ibrahimayhan.entities.Author;
import com.ibrahimayhan.repository.AuthorRepository;
import com.ibrahimayhan.service.IAuthorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements IAuthorService {

	private final AuthorRepository authorRepository;

	
	@Override
	public List<AuthorResponseDto> getAllAuthors() {
		
		List<Author> authorList=authorRepository.findAll();
		
		List<AuthorResponseDto> authorDtoList=new ArrayList<>();
		
		if(authorList!=null && !authorList.isEmpty()) {
			
			for (Author author : authorList) {
				AuthorResponseDto authorDto=new AuthorResponseDto();
				BeanUtils.copyProperties(author, authorDto);
				authorDtoList.add(authorDto);
			}
		}
		
		return authorDtoList;
	}
}
