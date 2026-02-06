package com.ibrahimayhan.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.ibrahimayhan.dto.PublisherDetailResponseDto;
import com.ibrahimayhan.dto.PublisherRequestDto;
import com.ibrahimayhan.dto.PublisherResponseDto;
import com.ibrahimayhan.entities.Publisher;

@Mapper(
	    componentModel = "spring",
	    uses = BookMapper.class,
	    unmappedTargetPolicy = ReportingPolicy.ERROR
	)
public interface PublisherMapper {

	// ENTITY -> sade response (id + name)
    PublisherResponseDto toResponseDto(Publisher publisher);

    List<PublisherResponseDto> toResponseDtoList(List<Publisher> publishers);
    
    

    // ENTITY -> detay response (id + name + books)
    PublisherDetailResponseDto toDetailResponseDto(Publisher publisher);

    List<PublisherDetailResponseDto> toDetailResponseDtoList(List<Publisher> publishers);

    
    // REQUEST -> ENTITY (create/update)
    @Mapping(target = "publisherId", ignore = true)
    @Mapping(target = "books", ignore = true)
    Publisher toEntity(PublisherRequestDto dto);
}
