package com.example.book.shop.service;

import com.example.book.shop.entity.book.Book;
import com.example.book.shop.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public final class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Book book) {
        bookRepository.delete(book);
    }
}
