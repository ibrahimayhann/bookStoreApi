package com.ibrahimayhan.service;

import java.util.List;

import com.ibrahimayhan.dto.AuthorResponseDto;

public interface IAuthorService {

	public List<AuthorResponseDto> getAllAuthors();
}
