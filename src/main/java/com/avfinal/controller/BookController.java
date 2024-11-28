package com.avfinal.controller;

import com.avfinal.business.BookBusiness;
import com.avfinal.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookBusiness bookBusiness;

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable String id) throws ClassNotFoundException {
        return bookBusiness.getById(id);
    }

    @GetMapping("")
    public List<Book> getBooks() {
        return bookBusiness.getAll();
    }

    @PostMapping("")
    public Book addBook(@RequestBody Book book) {
        return bookBusiness.save(book);
    }

    @PutMapping("")
    public Book updateBook(@RequestBody Book book) throws ClassNotFoundException {
        return bookBusiness.update(book);
    }

    @PutMapping("/rent")
    public Book rent(@RequestBody Book book) throws ClassNotFoundException {
        return bookBusiness.rent(book);
    }

    @PutMapping("/return")
    public Book returnBook(@RequestBody Book book) throws ClassNotFoundException {
        return bookBusiness.returnBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable String id) throws ClassNotFoundException {
        bookBusiness.deleteById(id);
    }

}
