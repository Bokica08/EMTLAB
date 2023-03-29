package com.example.labemt.controllers;

import com.example.labemt.models.Author;
import com.example.labemt.models.DTO.AuthorDTO;
import com.example.labemt.services.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;

    }
    @GetMapping
    public List<Author> findAll()
    {
        return authorService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Author> findById(@PathVariable Long id)
    {
        return authorService.findById(id)
                .map(body->ResponseEntity.ok().body(body))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Author> save(@RequestBody AuthorDTO authorDTO)
    {
        return authorService.save(authorDTO)
                .map(body->ResponseEntity.ok().body(body))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Author> save(@PathVariable Long id, @RequestBody AuthorDTO authorDTO)
    {
        return authorService.edit(id,authorDTO)
                .map(body->ResponseEntity.ok().body(body))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Author> delete(@PathVariable Long id)
    {
        authorService.deleteById(id);
        if(authorService.findById(id).isEmpty())
        {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
