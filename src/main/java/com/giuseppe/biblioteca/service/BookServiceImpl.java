package com.giuseppe.biblioteca.service;

import com.giuseppe.biblioteca.model.Book;
import com.giuseppe.biblioteca.model.BookDTO;
import com.giuseppe.biblioteca.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements IBookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    private BookDTO toDTO(Book bookEntity) {
        return new BookDTO(
                bookEntity.getId(),
                bookEntity.getTitle(),
                bookEntity.getAuthor(),
                bookEntity.getAnno(),
                bookEntity.getGenre()
        );
    }

    private Book toEntity(BookDTO bookDTO) {
        return new Book(
                bookDTO.id(),
                bookDTO.title(),
                bookDTO.author(),
                bookDTO.year(),
                bookDTO.genre()
        );
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id) {
        return bookRepository.findById(id).map(this::toDTO).orElseThrow();
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        return toDTO(bookRepository.save(
                toEntity(bookDTO)
        ));
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book book = bookRepository.findById(id).orElseThrow();

        book.setTitle(bookDTO.title());
        book.setAuthor(bookDTO.author());
        book.setAnno(bookDTO.year());
        book.setGenre(bookDTO.genre());

        return toDTO(bookRepository.save(book));
    }

    @Override
    public boolean deleteBook(Long id) {
        if (bookRepository.findById(id).isPresent()) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<BookDTO> getBooksByAuthor(String author) {
        return List.of();
    }

    @Override
    public List<BookDTO> getBooksByGenre(String genre) {
        return List.of();
    }

    @Override
    public List<BookDTO> searchBooksByTitle(String title) {
        return List.of();
    }

    @Override
    public List<BookDTO> getBooksPublishedBefore(int year) {
        return List.of();
    }

    @Override
    public int countBooksByAuthor(String author) {
        return 0;
    }

    @Override
    public List<BookDTO> getAllBooksSortedByYearDesc() {
        return List.of();
    }

    @Override
    public List<BookDTO> searchBooksByTitleOrAuthor(String keyword) {
        return List.of();
    }
}
