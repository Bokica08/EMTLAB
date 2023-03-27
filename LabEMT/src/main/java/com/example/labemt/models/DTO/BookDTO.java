package com.example.labemt.models.DTO;

import com.example.labemt.models.enumerations.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BookDTO {
    private String name;
    private Genre genre;
    private Long authorId;
    private Integer availableCopies;
}
