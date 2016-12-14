package com.bookworld.Service;

/**
 * Created by vova on 09.12.2016.
 */

import com.bookworld.Enitity.Book;
import com.bookworld.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vova on 08.12.2016.
 */
@Component
public class BookService {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        Iterable<Book> books =  bookRepository.findAll();
        return makeCollection(books);
    }

    public void update(Book book) {
        bookRepository.save(book);
    }

    public static <Book> List<Book> makeCollection(Iterable<Book> iter) {
        List list = new ArrayList<Book>();
        for (Book item : iter) {
            list.add(item);
        }
        return list;
    }

    public Book insertBook(Book book) {
        return bookRepository.save(book);
    }
}
