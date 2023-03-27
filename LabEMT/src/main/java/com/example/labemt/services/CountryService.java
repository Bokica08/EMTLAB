package com.example.labemt.services;

import com.example.labemt.models.Country;
import com.example.labemt.models.DTO.CountryDTO;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    Optional<Country> save(CountryDTO countryDTO);
    Optional<Country> edit(Long id, CountryDTO countryDTO);
    void deleteById(Long id);
}
