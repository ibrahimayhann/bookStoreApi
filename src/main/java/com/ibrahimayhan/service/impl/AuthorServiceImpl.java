package com.ibrahimayhan.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ibrahimayhan.dto.AuthorResponseDto;
import com.ibrahimayhan.entities.Author;
import com.ibrahimayhan.mapper.AuthorMapper;
import com.ibrahimayhan.repository.AuthorRepository;
import com.ibrahimayhan.service.IAuthorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements IAuthorService {

	private final AuthorRepository authorRepository;
	
	private final AuthorMapper authorMapper;

	
	@Override
	public List<AuthorResponseDto> getAllAuthors() {
		
		List<Author> authorList=authorRepository.findAll();
		if(authorList==null||authorList.isEmpty())
			return List.of();
		
		return authorMapper.toResponseDtoList(authorList);
	}
}
