package com.ibrahimayhan.controller;

import java.util.List;

import com.ibrahimayhan.dto.AuthorResponseDto;

public interface IAuthorController {

	public List<AuthorResponseDto>  getAllAuthors();
}
