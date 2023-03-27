package com.example.labemt.services.impl;

import com.example.labemt.exceptions.AuthorNotFound;
import com.example.labemt.exceptions.BookNotFound;
import com.example.labemt.exceptions.NumberOfCopiesAvailableNotPositive;
import com.example.labemt.models.Author;
import com.example.labemt.models.Book;
import com.example.labemt.models.DTO.BookDTO;
import com.example.labemt.repository.AuthorRepository;
import com.example.labemt.repository.BookRepository;
import com.example.labemt.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(BookDTO bookDTO) {
        Author author=authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(()->new AuthorNotFound(bookDTO.getAuthorId()));
        Book book=new Book(bookDTO.getName(),bookDTO.getGenre(),author,bookDTO.getAvailableCopies());
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, BookDTO bookDTO) {
        Book book=bookRepository.findById(id)
                .orElseThrow(()->new BookNotFound(id));
        Author author=authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(()->new AuthorNotFound(bookDTO.getAuthorId()));
        book.setName(bookDTO.getName());
        book.setGenre(bookDTO.getGenre());
        book.setAuthor(author);
        book.setAvailableCopies(bookDTO.getAvailableCopies());
        return Optional.of(bookRepository.save(book));

    }

    @Override
    public void deleteById(Long id) {
    bookRepository.deleteById(id);

    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()->new BookNotFound(id));
        if(book.getAvailableCopies()<=0)
        {
            throw new NumberOfCopiesAvailableNotPositive(id);
        }
        int copies = book.getAvailableCopies();
        book.setAvailableCopies(copies-1);
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Page<Book> findAllByPagination(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }
}
