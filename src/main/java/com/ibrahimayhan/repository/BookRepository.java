package com.ibrahimayhan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibrahimayhan.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
	@Query("select b from Book b where b.publishYear > :year")
	List<Book> findBooksPublishedAfter(@Param("year") int year);

}
