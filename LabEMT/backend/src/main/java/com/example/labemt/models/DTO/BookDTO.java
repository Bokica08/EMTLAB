package com.example.labemt.models.DTO;

import com.example.labemt.models.enumerations.Category;
import lombok.Data;

@Data
public class BookDTO {
    private String name;
    private Category category;
    private Long authorId;
    private Integer availableCopies;
}
