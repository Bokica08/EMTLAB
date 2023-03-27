package com.example.labemt.services;

import com.example.labemt.models.Book;
import com.example.labemt.models.DTO.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Optional<Book> save(BookDTO bookDTO);
    Optional<Book> edit(Long id, BookDTO bookDTO);
    void deleteById(Long id);
    Optional<Book> markAsTaken(Long id);
    Page<Book> findAllByPagination(Pageable pageable);
}
