package com.ibrahimayhan.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.ibrahimayhan.dto.BookRequestDto;
import com.ibrahimayhan.dto.BookResponseDto;
import com.ibrahimayhan.entities.Author;
import com.ibrahimayhan.entities.Book;
import com.ibrahimayhan.entities.Publisher;
import com.ibrahimayhan.mapper.BookMapper;
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
	
	private final BookMapper bookMapper;


	
	//tüm kitapları döner
	@Override
	public List<BookResponseDto> getAllBooks() {
				
		return bookMapper.toDtoList(bookRepository.findAll());
	}
	
	
	
	//kullanıcıdan gelen string prefix ile kitap adına  göre filtreleme yapıp uygun kitapları döner 
	@Override
	public List<BookResponseDto> getBooksStartWith(String prefix) {
		
		if (prefix == null || prefix.isBlank()) 
			return List.of();

	    List<Book> bookFromDb= bookRepository.findByTitleStartingWithIgnoreCase(prefix);
	    if(bookFromDb==null||bookFromDb.isEmpty())
	    	return List.of();
	    
	    return bookMapper.toDtoList(bookFromDb);
	            
	}

	
		//Save book'u  fazla şişirmemek ve okunabilirlik artırmak için 3 tane yardımcı metodu  var 
		@Transactional
		@Override
		public BookResponseDto saveBook(BookRequestDto request) {
			
			Book book=bookMapper.toEntity(request);
			
			book.setAuthor(findOrCreateAuthor(request.getAuthorNameSurname()));
			book.setPublisher(findOrCreatePublisher(request.getPublisherName()));
			

			return bookMapper.toDto(bookRepository.save(book));
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
	

	
	//kullanıcıdan gelen year a göre filtreleme yapıp uygun booksları döner
	@Override
	public List<BookResponseDto> getBooksPublishedAfter(int year) {
				
		List<Book> booksFromDb=bookRepository.findBooksPublishedAfter(year);
		if(booksFromDb.isEmpty())
			return List.of();
		
		return bookMapper.toDtoList(booksFromDb);
	}

	
	
	
	//kullanıcıdan id ve bookRequestDto alarak güncelleyip dtoresponse book olarak döner
	@Transactional
	@Override
	public BookResponseDto updateBook(Long id, BookRequestDto requestDto) {
		
		Optional<Book> optioanlBook=bookRepository.findById(id);
		if(optioanlBook.isEmpty()) {return null;}//notFoundexception fırlatılmalı
		
		Book bookFromDb=optioanlBook.get();
		bookMapper.patchEntity(requestDto, bookFromDb);
		
		Author author=findOrCreateAuthor(requestDto.getAuthorNameSurname());
		bookFromDb.setAuthor(author);

		Publisher publisher=findOrCreatePublisher(requestDto.getPublisherName());
		bookFromDb.setPublisher(publisher);
				
		return bookMapper.toDto(bookRepository.save(bookFromDb));
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
