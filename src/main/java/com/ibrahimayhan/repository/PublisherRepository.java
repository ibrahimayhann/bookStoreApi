package com.ibrahimayhan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ibrahimayhan.entities.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,Long> {
	
	Optional<Publisher> findByPublisherName(String publisherName);
	
	
	@Query("""
			SELECT DISTINCT p
			FROM Publisher p
			LEFT JOIN FETCH p.books b
			LEFT JOIN FETCH b.author

			""")
	List<Publisher> findPublisherWithBooksAndAuthors();



}
