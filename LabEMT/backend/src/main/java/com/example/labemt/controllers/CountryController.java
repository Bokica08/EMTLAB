package com.example.labemt.controllers;

import com.example.labemt.models.Country;
import com.example.labemt.models.DTO.CountryDTO;
import com.example.labemt.services.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;

    }
    @GetMapping
    public List<Country> findAll()
    {
        return countryService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Country> findById(@PathVariable Long id)
    {
        return countryService.findById(id)
                .map(body->ResponseEntity.ok().body(body))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Country> save(@RequestBody CountryDTO countryDTO)
    {
        return countryService.save(countryDTO)
                .map(body->ResponseEntity.ok().body(body))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Country> save(@PathVariable Long id, @RequestBody CountryDTO countryDTO)
    {
        return countryService.edit(id,countryDTO)
                .map(body->ResponseEntity.ok().body(body))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Country> delete(@PathVariable Long id)
    {
        countryService.deleteById(id);
        if(countryService.findById(id).isEmpty())
        {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
