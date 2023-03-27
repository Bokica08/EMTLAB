package com.example.labemt.services.impl;

import com.example.labemt.exceptions.AuthorNotFound;
import com.example.labemt.exceptions.CountryNotFound;
import com.example.labemt.models.Author;
import com.example.labemt.models.Country;
import com.example.labemt.models.DTO.AuthorDTO;
import com.example.labemt.repository.AuthorRepository;
import com.example.labemt.repository.CountryRepository;
import com.example.labemt.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(AuthorDTO authorDTO) {
        Country country = countryRepository.findById(authorDTO.getCountryId())
                .orElseThrow(()->new CountryNotFound(authorDTO.getCountryId()));
        Author author=new Author(authorDTO.getName(),authorDTO.getSurname(),country);
        return Optional.of(authorRepository.save(author));

    }

    @Override
    public Optional<Author> edit(Long id, AuthorDTO authorDTO) {
        Author author = authorRepository.findById(id)
                .orElseThrow(()->new AuthorNotFound(id));
        Country country = countryRepository.findById(authorDTO.getCountryId())
                .orElseThrow(()->new CountryNotFound(authorDTO.getCountryId()));
        author.setCountry(country);
        author.setName(authorDTO.getName());
        author.setSurname(authorDTO.getSurname());
        return Optional.of(authorRepository.save(author));
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);

    }
}
