package com.example.labemt.exceptions;

public class NumberOfCopiesAvailableNotPositive extends RuntimeException{
    public NumberOfCopiesAvailableNotPositive(Long id)
    {
        super(String.format("Book with id %d has no copies left", id));
    }
}
