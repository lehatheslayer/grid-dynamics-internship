package com.example.book.shop.controller;

import com.example.book.shop.entity.book.Book;
import com.example.book.shop.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class CatalogController {
    private final BookService bookService;

    public CatalogController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String showCatalog(Map<String, Object> model) {
        model.put("book_to_del", new Book());
        model.put("books", bookService.findAll());

        return "catalog";
    }
}
