package com.example.book.shop.book.service;

import com.example.book.shop.entity.book.Book;
import com.example.book.shop.repository.BookRepository;
import com.example.book.shop.service.BookService;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllTest() {
        final Book book1 = new Book("War and piece", "WaP desc", "Tolstoy", 1999D);
        final Book book2 = new Book("Evgeniy Onegin", "EO desc", "Pushkin", 500D);
        final Book book3 = new Book("Woe from Wit", "WfW desc", "Griboyedov", 1499D);
        final List<Book> list = List.of(book1, book2, book3);

        Mockito.when(bookRepository.findAll()).thenReturn(list);

        final List<Book> result = (List<Book>) bookService.findAll();

        assertEquals(3, result.size());
        Mockito.verify(bookRepository, Mockito.times(1)).findAll();
    }
}
