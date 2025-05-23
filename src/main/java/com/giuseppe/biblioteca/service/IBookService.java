package com.giuseppe.biblioteca.service;

import com.giuseppe.biblioteca.model.BookDTO;

import java.util.List;

public interface IBookService {
    List<BookDTO> getAllBooks();
    BookDTO getBookById(Long id);
    BookDTO createBook(BookDTO bookDTO);
    BookDTO updateBook(Long id, BookDTO bookDTO);
    boolean deleteBook(Long id);
}
