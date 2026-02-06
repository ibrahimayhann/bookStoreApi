package com.ibrahimayhan.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.ibrahimayhan.dto.BookRequestDto;
import com.ibrahimayhan.dto.BookResponseDto;
import com.ibrahimayhan.entities.Book;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.ERROR)//unmappedTargetPolicy = ReportingPolicy.ERROR // DTO'ya yeni alan ekleyince map etmeyi unutursam compile-time'da yakalar
public interface BookMapper {

		// ENTITY -> RESPONSE DTO
		@Mapping(source = "author.authorNameSurname", target = "authorNameSurname")
	    @Mapping(source = "publisher.publisherName", target = "publisherName")
	    BookResponseDto toDto(Book book);

		
	    List<BookResponseDto> toDtoList(List<Book> books);
	    
	    
	    // REQUEST DTO -> ENTITY
	    @Mapping(target = "bookId", ignore = true)//param ile kullanıcıdan gelecek
	    @Mapping(target = "publisher", ignore = true)//serviste id ile bulup kendim set edeceğim
	    @Mapping(target = "author", ignore = true)//serviste id ile bulup kendim set edeceğim
	    Book toEntity(BookRequestDto dto);
	    
	    
	    //PATCH: null gelen alanları IGNORE et (kısmi güncelleme)
	    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)//RequestDTO’daki bir alan null ise, entity’deki mevcut değeri EZME
	    @Mapping(target = "bookId", ignore = true)
	    @Mapping(target = "publisher", ignore = true)
	    @Mapping(target = "author", ignore = true)
	    void patchEntity(BookRequestDto dto, @MappingTarget Book book);
	    
	    
}
