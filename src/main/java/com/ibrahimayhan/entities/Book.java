package com.ibrahimayhan.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bookId;
	
	
	private String title;
	
	
	
	private BigDecimal price;
	
	
	
	@Column(nullable = false,unique = true,length = 13)
	private String isbn13;
	

	@Column(name = "publish_year")
	private Integer publishYear;
	
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publisher_id",nullable = false)
	private Publisher publisher;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id",nullable = false)
	private Author author;
	
	
	
	
}
