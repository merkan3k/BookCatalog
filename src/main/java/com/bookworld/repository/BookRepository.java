package com.bookworld.repository;

import com.bookworld.Enitity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by vova on 10.12.2016.
 */
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findByTitle(String title);
}
