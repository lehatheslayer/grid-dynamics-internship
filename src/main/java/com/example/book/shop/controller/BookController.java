package com.example.book.shop.controller;

import com.example.book.shop.entity.book.Book;
import com.example.book.shop.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/add/book")
    public String showAddingForm(Map<String, Object> model) {
        model.put("bookForm", new Book());
        return "addBook";
    }

    @PostMapping("/add/book")
    public String saveBook(Book book, Map<String, Object> model) {
        bookService.save(book);

        return showAddingForm(model);
    }

    @PostMapping("/delete/book")
    public ModelAndView deleteBook(Book book, Map<String, Object> model) {
        bookService.delete(book);

        return new ModelAndView("redirect:/", model);
    }
}
