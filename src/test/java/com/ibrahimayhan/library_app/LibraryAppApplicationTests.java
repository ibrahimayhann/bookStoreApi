/*
package com.ibrahimayhan.library_app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.ibrahimayhan.dto.BookRequestDto;
import com.ibrahimayhan.dto.BookResponseDto;
import com.ibrahimayhan.entities.Author;
import com.ibrahimayhan.entities.Book;
import com.ibrahimayhan.entities.Publisher;
import com.ibrahimayhan.repository.AuthorRepository;
import com.ibrahimayhan.repository.BookRepository;
import com.ibrahimayhan.repository.PublisherRepository;
import com.ibrahimayhan.service.impl.BookServiceImpl;
import com.ibrahimayhan.starter.LibraryAppApplication;

@SpringBootTest(classes = {LibraryAppApplication.class})
class LibraryAppApplicationTests {

	 @Mock
	    private BookRepository bookRepository;

	    @Mock
	    private AuthorRepository authorRepository;

	    @Mock
	    private PublisherRepository publisherRepository;

	    @InjectMocks
	    private BookServiceImpl bookService;

	    @BeforeEach
	    void setup() {
	        MockitoAnnotations.openMocks(this);
	    }

	    // 1️⃣ saveBook testi
	    @Test
	    void testSaveBook() {
	        BookRequestDto request = new BookRequestDto();
	        request.setTitle("Spring Boot");
	        request.setPrice(100.00);
	        request.setAuthorNameSurname("Alice");
	        request.setPublisherName("OReilly");

	        Author savedAuthor = new Author();
	        savedAuthor.setAuthorNameSurname("alice");

	        Publisher savedPublisher = new Publisher();
	        savedPublisher.setPublisherName("oreilly");

	        Book savedBook = new Book();
	        savedBook.setId(1L);
	        savedBook.setTitle("Spring Boot");
	        savedBook.setPrice(100.0);
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
	        assertEquals("Spring Boot", response.getTitle());
	        assertEquals("alice", response.getAuthorNameSurname());
	        assertEquals("oreilly", response.getPublisherName());

	        verify(authorRepository, times(1)).save(any(Author.class));
	        verify(publisherRepository, times(1)).save(any(Publisher.class));
	        verify(bookRepository, times(1)).save(any(Book.class));
	    }

	    // 2️⃣ getBooksStartWith testi
	    @Test
	    void testGetBooksStartWith() {
	        Author author = new Author();
	        author.setAuthorNameSurname("John Doe");
	        Publisher publisher = new Publisher();
	        publisher.setPublisherName("Pearson");

	        Book book1 = new Book();
	        book1.setTitle("Java Basics");
	        book1.setAuthor(author);
	        book1.setPublisher(publisher);

	        Book book2 = new Book();
	        book2.setTitle("Spring Boot");
	        book2.setAuthor(author);
	        book2.setPublisher(publisher);

	        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

	        List<BookResponseDto> result = bookService.getBooksStartWith("Java");

	        assertEquals(1, result.size());
	        assertEquals("Java Basics", result.get(0).getTitle());
	        assertEquals("John Doe", result.get(0).getAuthorNameSurname());
	        assertEquals("Pearson", result.get(0).getPublisherName());
	    }

}
*/