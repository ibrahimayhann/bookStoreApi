package com.ibrahimayhan.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.ibrahimayhan.dto.AuthorRequestDto;
import com.ibrahimayhan.dto.AuthorResponseDto;
import com.ibrahimayhan.entities.Author;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AuthorMapper {
	
	//Entity ->ResponseDto
	AuthorResponseDto toResponseDto(Author author);
	List<AuthorResponseDto> toResponseDtoList(List<Author> authors);
	
	
	//RequestDto -> Entity
	@Mapping(target = "authorId",ignore = true)
	@Mapping(target = "books",ignore = true)
	Author toEntity(AuthorRequestDto dto);

}
