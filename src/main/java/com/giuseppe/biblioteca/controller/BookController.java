package com.giuseppe.biblioteca.controller;

import com.giuseppe.biblioteca.model.BookDTO;
import com.giuseppe.biblioteca.service.IBookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> getAll() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(bookService.getBookById(id));
        } catch (NoSuchElementException nseex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BookDTO book) {
        if (book.id() != null)
            return ResponseEntity.badRequest().body("Non includere campo id, ci pensa il database");

        return ResponseEntity.ok(bookService.createBook(book));
    }

    @PutMapping("{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Long id, @RequestBody BookDTO book) {
        try {
            return ResponseEntity.ok(bookService.updateBook(id, book));
        } catch (NoSuchElementException nseex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean deleted = bookService.deleteBook(id);
        if (deleted) {
            return ResponseEntity.ok("Libro con id =" + id + " eliminato con successo.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Controller con Nuove API
    // Ho aggiunto 7 nuovi endpoint REST:
    // GET /api/books/author/{author} - Libri per autore
    // GET /api/books/genre/{genre} - Libri per genere
    // GET /api/books/search/title?title=xyz - Ricerca per titolo
    // GET /api/books/published-before/{year} - Libri pubblicati prima di un anno
    // GET /api/books/count/author/{author} - Conteggio libri per autore
    // GET /api/books/sorted-by-year - Libri ordinati per anno
    // GET /api/books/search?keyword=xyz - Ricerca per titolo o autore
    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookDTO>> getBooksByAuthor(@PathVariable String author) {
        List<BookDTO> books = bookService.getBooksByAuthor(author);
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<BookDTO>> getBooksByGenre(@PathVariable String genre) {
        List<BookDTO> books = bookService.getBooksByGenre(genre);
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<BookDTO>> searchBooksByTitle(@RequestParam String title) {
        List<BookDTO> books = bookService.searchBooksByTitle(title);
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }

    @GetMapping("/published-before/{year}")
    public ResponseEntity<List<BookDTO>> getBooksPublishedBefore(@PathVariable int year) {
        List<BookDTO> books = bookService.getBooksPublishedBefore(year);
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }

    @GetMapping("/count/author/{author}")
    public ResponseEntity<Integer> countBooksByAuthor(@PathVariable String author) {
        int count = bookService.countBooksByAuthor(author);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/sorted-by-year")
    public ResponseEntity<List<BookDTO>> getAllBooksSortedByYear() {
        List<BookDTO> books = bookService.getAllBooksSortedByYearDesc();
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> searchBooksByTitleOrAuthor(@RequestParam String keyword) {
        List<BookDTO> books = bookService.searchBooksByTitleOrAuthor(keyword);
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }
}
