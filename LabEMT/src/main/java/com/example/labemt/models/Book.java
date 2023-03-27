package com.example.labemt.models;

import com.example.labemt.models.enumerations.Genre;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Book {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer availableCopies;
    @ManyToOne
    private Author author;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    public Book(String name, Genre genre, Author author, Integer availableCopies) {
        this.name = name;
        this.genre = genre;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}
