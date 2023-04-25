package com.example.labemt.services;

import com.example.labemt.models.Author;
import com.example.labemt.models.DTO.AuthorDTO;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    Optional<Author> save(AuthorDTO authorDTO);
    Optional<Author> edit(Long id,AuthorDTO authorDTO);
    void deleteById(Long id);

}
