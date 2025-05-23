package com.giuseppe.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private int publication_year;
    private String genre;

    public Book() {}

    public Book(Long id, String title, String author, int pubblication_year, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publication_year = publication_year;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getPublication_year() {
        return pubblication_year;
    }

    public void setPublication_year(int publication_year) {
        this.publication_year = publication_year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
