package com.bookworld;


import com.bookworld.Enitity.Book;
import com.bookworld.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;


@SpringBootApplication
public class BookCatalogApplication {

	private static final Logger log = LoggerFactory.getLogger(BookCatalogApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookCatalogApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Book("The Hunger Games", "Suzanne Collins", 2015, BigDecimal.valueOf(20.99)));
			repository.save(new Book("To Kill a Mockingbird", "Harper Lee", 2015, BigDecimal.valueOf(20.99)));
			repository.save(new Book("Pride and Prejudice", "Jane Austen", 2015, BigDecimal.valueOf(20.99)));
			repository.save(new Book("The Book Thief", "Markus Zusak", 2015, BigDecimal.valueOf(20.99)));
			repository.save(new Book("Animal Farm ", "George Orwell", 2015, BigDecimal.valueOf(20.99)));

			// fetch all customers
			log.info("Book found with findAll():");
			log.info("-------------------------------");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
			log.info("");

			// fetch an individual book by ID
			Book book = repository.findOne(1L);
			log.info("Book found with findOne(1L):");
			log.info("--------------------------------");
			log.info(book.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('1984'):");
			log.info("--------------------------------------------");
			for (Book book1 : repository.findByTitle("1984")) {
				log.info(book1.toString());
			}
			log.info("");
		};
	}


}
