package com.ibrahimayhan.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ibrahimayhan.dto.BookRequestDto;
import com.ibrahimayhan.dto.BookResponseDto;
import com.ibrahimayhan.entities.Author;
import com.ibrahimayhan.entities.Book;
import com.ibrahimayhan.entities.Publisher;
import com.ibrahimayhan.repository.AuthorRepository;
import com.ibrahimayhan.repository.BookRepository;
import com.ibrahimayhan.repository.PublisherRepository;
import com.ibrahimayhan.service.IBookService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService{

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;
	private final PublisherRepository publisherRepository;

	
	//tüm kitapları döner
	@Override
	public List<BookResponseDto> getAllBooks() {
		List<Book> bookList=bookRepository.findAll();
		
		List<BookResponseDto> bookDtoList=new ArrayList<>();
		
		if(bookList!= null&& !bookList.isEmpty()) {
			for (Book book : bookList) {
				BookResponseDto bookDto =new BookResponseDto();
				BeanUtils.copyProperties(book, bookDto);
				bookDto.setAuthorNameSurname(book.getAuthor().getAuthorNameSurname());
				bookDto.setPublisherName(book.getPublisher().getPublisherName());
				bookDtoList.add(bookDto);
			}
		}
		return bookDtoList;
	}
	
	//kullanıcıdan gelen string prefix ile kitap adına  göre filtreleme yapıp uygun kitapları döner 
	@Override
	public List<BookResponseDto> getBooksStartWith(String prefix) {
		
		if (prefix == null || prefix.isBlank()) return List.of();

	    return bookRepository.findByTitleStartingWithIgnoreCase(prefix)
	            .stream()
	            //.filter(book -> book.getTitle() != null)
	            //.filter(book -> book.getTitle().toUpperCase().startsWith(prefix.toUpperCase()))
	            //findAll dan kaçınarak repostoryde ıgnore case yaptığım için filter gerek kalmadı
	            .map(book -> {
	                BookResponseDto dto = new BookResponseDto();

	                BeanUtils.copyProperties(book, dto);

	                if (book.getAuthor() != null  ) {
	                    dto.setAuthorNameSurname(book.getAuthor().getAuthorNameSurname());
	                }
	                
	                if(book.getPublisher()!= null) {
	                   dto.setPublisherName(book.getPublisher().getPublisherName());
	                   }

	                
	                return dto;
	            })
	            .toList();
	}

		//Save book'u  fazla şişirmemek ve okunabilirlik artırmak için 3 tane yardımcı metodu  var 
		@Transactional
		@Override
		public BookResponseDto saveBook(BookRequestDto request) {
			
			Book book=new Book();
			
			BeanUtils.copyProperties(request, book);

			book.setAuthor(findOrCreateAuthor(request.getAuthorNameSurname()));
			book.setPublisher(findOrCreatePublisher(request.getPublisherName()));
			
			Book savedBook=bookRepository.save(book);
			BookResponseDto bookResponseDto=new BookResponseDto();
			BeanUtils.copyProperties(savedBook, bookResponseDto);
			
			bookResponseDto.setPublisherName(savedBook.getPublisher().getPublisherName());
			bookResponseDto.setAuthorNameSurname(savedBook.getAuthor().getAuthorNameSurname());
			return bookResponseDto;
		}

	
	//findorcreateauthor ve findorcreatepublisher metotlarında string kısımları normalize eden yardımzı metot
	private String normalizeName(String name) {
		if(name==null) {
			return null;
		}
		return name.trim().toLowerCase().replaceAll("\\s+", " ");
	}

	
	
	
	//Kitap save ederken request ile gelen string authora göre authorRepostoryde arama yapan 
	//varsa eşleşen authoru , yoksa yeni author kaydedip kaydedilen authoru dönen yardımcı metot
	private Author findOrCreateAuthor(String authorNameSurname) {
		
		String normalizedName=normalizeName(authorNameSurname);
		
		return authorRepository.findByAuthorNameSurname(normalizedName).orElseGet(()->{
			Author author=new Author();
			author.setAuthorNameSurname(normalizedName);
			return authorRepository.save(author);
		});//bu metot her 2 durumda da  bize bir author dönecek
	}
	
	
	//Kitap save ederken request ile gelen string publisher a  göre publisher repostoryde  arama yapan 
	//varsa eşleşen publisher , yoksa yeni publisher kaydedip kaydedilen publisher dönen yardımcı metot
	private Publisher findOrCreatePublisher(String publisherName) {
		
		String normalizedName=normalizeName(publisherName);
		
		return publisherRepository.findByPublisherName(normalizedName).orElseGet(()->{
			Publisher publisher =new Publisher();
			publisher.setPublisherName(normalizedName);
			return publisherRepository.save(publisher);
		});
		
	}
	

	
	//kullanıcıdan gelen year a göre filtreleme yapıp döner
	@Override
	public List<BookResponseDto> getBooksPublishedAfter(int year) {
		
		List<BookResponseDto> dtoBooksList=new ArrayList<>();
		
		List<Book> books=bookRepository.findBooksPublishedAfter(year);
		if(books==null || books.isEmpty())
			return List.of();
		for (Book book : books) {
			BookResponseDto dtoBook=new BookResponseDto();
			BeanUtils.copyProperties(book, dtoBook);
			dtoBooksList.add(dtoBook);
		}
		return dtoBooksList;
	}

	
	
	
	//kullanıcıdan id ve bookRequestDto alarak güncelleyip dtoresponse book olarak döner
	@Transactional
	@Override
	public BookResponseDto updateBook(Long id, BookRequestDto requestDto) {
		
		Optional<Book> optioanlBook=bookRepository.findById(id);
		if(optioanlBook.isEmpty()) {return null;}//notFoundexception fırlatılmalı
		
		Book bookFromDb=optioanlBook.get();
		BeanUtils.copyProperties(requestDto,bookFromDb);
		
		
		Author author=findOrCreateAuthor(requestDto.getAuthorNameSurname());
		bookFromDb.setAuthor(author);

		Publisher publisher=findOrCreatePublisher(requestDto.getPublisherName());
		bookFromDb.setPublisher(publisher);
		
		Book savedBook=bookRepository.save(bookFromDb);
		
		BookResponseDto bookResponseDto=new BookResponseDto();
		BeanUtils.copyProperties(savedBook, bookResponseDto);
		
		bookResponseDto.setPublisherName(savedBook.getPublisher().getPublisherName());
		bookResponseDto.setAuthorNameSurname(savedBook.getAuthor().getAuthorNameSurname());
		
		return bookResponseDto;
	}

	
	@Transactional
	@Override
	public void deleteBook(Long id) {
		Book book = bookRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Book not found"));
	//globalexceptiondahandlerda  sadece validasyon hatalarını handle ettiğim için bu hata catch edilmeyecek
	    bookRepository.delete(book);
		
	}
	
	
	
	
}
