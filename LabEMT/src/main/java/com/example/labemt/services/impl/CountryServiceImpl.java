package com.example.labemt.services.impl;

import com.example.labemt.exceptions.CountryNotFound;
import com.example.labemt.models.Country;
import com.example.labemt.models.DTO.CountryDTO;
import com.example.labemt.repository.CountryRepository;
import com.example.labemt.services.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(CountryDTO countryDTO) {
        Country country=new Country(countryDTO.getName(),countryDTO.getContinent());
        return Optional.of(countryRepository.save(country));
    }

    @Override
    public Optional<Country> edit(Long id, CountryDTO countryDTO) {
        Country country=countryRepository.findById(id)
                .orElseThrow(()-> new CountryNotFound(id));
        country.setContinent(countryDTO.getContinent());
        country.setName(countryDTO.getName());
        return Optional.of(countryRepository.save(country));
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);

    }
}
