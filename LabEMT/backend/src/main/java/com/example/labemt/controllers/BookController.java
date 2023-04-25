package com.example.labemt.controllers;

import com.example.labemt.models.Book;
import com.example.labemt.models.DTO.BookDTO;
import com.example.labemt.models.enumerations.Category;
import com.example.labemt.services.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;

    }
    @GetMapping
    public List<Book> findAll()
    {
        System.out.println(bookService.findAll());
        return bookService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id)
    {
        return bookService.findById(id)
                .map(body->ResponseEntity.ok().body(body))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Book> save(@RequestBody BookDTO bookDTO)
    {
        return bookService.save(bookDTO)
                .map(body->ResponseEntity.ok().body(body))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> save(@PathVariable Long id,@RequestBody BookDTO bookDTO)
    {
        return bookService.edit(id,bookDTO)
                .map(body->ResponseEntity.ok().body(body))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id)
    {
        bookService.deleteById(id);
        if(bookService.findById(id).isEmpty())
        {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/markAsTaken/{id}")
    public ResponseEntity<Book> markAsTaken(@PathVariable Long id)
    {
        return bookService.markAsTaken(id)
                .map(b->ResponseEntity.ok().body(b))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
    @GetMapping("/pagination")
    public List<Book> findAllByPagination(Pageable pageable)
    {
        return bookService.findAllByPagination(pageable).getContent();
    }
    @GetMapping("/category")
    public List<Category> getAllCategories()
    {
        return Arrays.stream(Category.values()).toList();
    }
}
