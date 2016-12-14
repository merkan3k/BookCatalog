package com.bookworld.Enitity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by vova on 09.12.2016.
 */
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "YEAR")
    private int year;

    @Column(name = "PRICE")
    private BigDecimal price;

    public Book() {}

    public Book(long id, String title, String author, int year, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
    }

    public Book(String title, String author, int year, BigDecimal price) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return getTitle() + " - " + getAuthor() + ", " + getYear() + " - " + getPrice();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
