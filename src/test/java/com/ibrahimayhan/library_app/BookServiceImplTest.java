
package com.ibrahimayhan.library_app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ibrahimayhan.dto.BookRequestDto;
import com.ibrahimayhan.dto.BookResponseDto;
import com.ibrahimayhan.entities.Author;
import com.ibrahimayhan.entities.Book;
import com.ibrahimayhan.entities.Publisher;
import com.ibrahimayhan.repository.AuthorRepository;
import com.ibrahimayhan.repository.BookRepository;
import com.ibrahimayhan.repository.PublisherRepository;
import com.ibrahimayhan.service.impl.BookServiceImpl;
import org.mockito.junit.jupiter.MockitoExtension;


//@SpringBootTest(classes = {LibraryAppApplication.class})
@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

	    @Mock
	    private BookRepository bookRepository;

	    @Mock
	    private AuthorRepository authorRepository;

	    @Mock
	    private PublisherRepository publisherRepository;

	    @InjectMocks
	    private BookServiceImpl bookService;


	    //saveBook testi
	    @Test
	    void testSaveBook() {
	        BookRequestDto request = new BookRequestDto();
	        request.setTitle("Kürk Mantolu Madonna");
	        request.setPrice(new BigDecimal(200.00));
	        request.setAuthorNameSurname("Sabahattin Ali");
	        request.setPublisherName("İş bankası yayınları");

	        Author savedAuthor = new Author();
	        savedAuthor.setAuthorNameSurname("Sabahattin Ali");

	        Publisher savedPublisher = new Publisher();
	        savedPublisher.setPublisherName("İş bankası yayınları");

	        Book savedBook = new Book();
	        savedBook.setBookId(1L);
	        savedBook.setTitle("Kürk Mantolu Madonna");
	        savedBook.setPrice(new BigDecimal(200.0));
	        savedBook.setAuthor(savedAuthor);
	        savedBook.setPublisher(savedPublisher);

	        // Mock davranış
	        when(authorRepository.findByAuthorNameSurname(anyString())).thenReturn(Optional.empty());
	        when(authorRepository.save(any(Author.class))).thenReturn(savedAuthor);

	        when(publisherRepository.findByPublisherName(anyString())).thenReturn(Optional.empty());
	        when(publisherRepository.save(any(Publisher.class))).thenReturn(savedPublisher);

	        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);

	        BookResponseDto response = bookService.saveBook(request);

	        assertNotNull(response);
	        assertEquals("Kürk Mantolu Madonna", response.getTitle());
	        assertEquals("Sabahattin Ali", response.getAuthorNameSurname());
	        assertEquals("İş bankası yayınları", response.getPublisherName());

	        verify(authorRepository, times(1)).save(any(Author.class));
	        verify(publisherRepository, times(1)).save(any(Publisher.class));
	        verify(bookRepository, times(1)).save(any(Book.class));
	    }

	    //getBooksStartWith testi
	    @Test
	    void testGetBooksStartWith() {
	    	
	        Author author = new Author();
	        author.setAuthorNameSurname("Victor Hugo");
	        
	        
	        Publisher publisher = new Publisher();
	        publisher.setPublisherName("Anonim Yayıncılık");

	        Book book1 = new Book();
	        book1.setTitle("Sefiller");
	        book1.setAuthor(author);
	        book1.setPublisher(publisher);

	        Book book2 = new Book();
	        book2.setTitle("Bir idam mahkumunun son günü");
	        book2.setAuthor(author);
	        book2.setPublisher(publisher);

	        //2 kitap db den return edilsin
	        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

	        
	        List<BookResponseDto> result = bookService.getBooksStartWith("Sef");

	        assertEquals(1, result.size());
	        assertEquals("Sefiller", result.get(0).getTitle());
	        assertEquals("Victor Hugo", result.get(0).getAuthorNameSurname());
	        assertEquals("Anonim Yayıncılık", result.get(0).getPublisherName());
	    }

}
