package com.ibrahimayhan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibrahimayhan.entities.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,Long> {
	
	
	Optional<Publisher> findByPublisherName(String publisherName);
	
	
	
	//yayınevlerini , yayınladıkları tüm kitaplar ve o kitapların yazarlarıyla beraber çekmek için query
	//n+1 sorgu sorunuyla karşılaşıp performansı düşürmemeye çalıştığım query
	@Query("""
			SELECT DISTINCT p
			FROM Publisher p
			LEFT JOIN FETCH p.books b
			LEFT JOIN FETCH b.author

			""")
	List<Publisher> findPublisherWithBooksAndAuthors();
	
	
	
	
	@Query("select p.publisherId from Publisher p order by  p.publisherId")
	List<Long> findTop2PublisherIds(Pageable pageable);

	@Query("""
			select distinct p from Publisher p
			left join fetch p.books b
			left join fetch b.author
			where p.publisherId in :ids
			""")
			List<Publisher> findPublishersWithBooksAndAuthorsByIds(@Param("ids") List<Long> ids);




}
