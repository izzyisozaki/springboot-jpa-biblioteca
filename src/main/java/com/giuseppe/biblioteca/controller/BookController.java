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
}
