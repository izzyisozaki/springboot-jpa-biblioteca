package com.giuseppe.biblioteca.repository;

import com.giuseppe.biblioteca.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthor(String author);

    List<Book> findByGenre(String genre);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByPublicationYearLessThan(int year);

    int countByAuthor(String author);

    @Query("SELECT b FROM Book b ORDER BY b.publication_year DESC")
    List<Book> findAllByOrderByYearDesc();

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> findByTitleOrAuthor(@Param("keyword")String keyword);

//    // Query personalizzate
//    @Query("SELECT * FROM BOOK WHERE ID = :id")
//    List<Book> getBooksById(Long id);
}
