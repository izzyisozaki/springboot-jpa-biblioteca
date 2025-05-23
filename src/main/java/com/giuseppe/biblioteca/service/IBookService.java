package com.giuseppe.biblioteca.service;

import com.giuseppe.biblioteca.model.BookDTO;

import java.util.List;

public interface IBookService {
    List<BookDTO> getAllBooks();
    BookDTO getBookById(Long id);
    BookDTO createBook(BookDTO bookDTO);
    BookDTO updateBook(Long id, BookDTO bookDTO);
    boolean deleteBook(Long id);

    // Metodi basati sui repository aggiunti
    List<BookDTO> getBooksByAuthor(String author);

    List<BookDTO> getBooksByGenre(String genre);

    List<BookDTO> searchBooksByTitle(String title);

    List<BookDTO> getBooksPublishedBefore(int year);

    int countBooksByAuthor(String author);

    List<BookDTO> getAllBooksSortedByYearDesc();

    List<BookDTO> searchBooksByTitleOrAuthor(String keyword);
}


