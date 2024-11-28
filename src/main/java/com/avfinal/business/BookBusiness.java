package com.avfinal.business;

import com.avfinal.model.Book;
import com.avfinal.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookBusiness {

    @Autowired private BookRepository bookRepository;

    public Book getById(String id) throws ClassNotFoundException {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty()){
            throw new ClassNotFoundException("O livro requisitado não existe");
        }

        return book.get();
    }

    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public Book update(Book book) throws ClassNotFoundException {
        Optional<Book> existentBook = bookRepository.findById(book.getId());
        if(existentBook.isEmpty()){
            throw new ClassNotFoundException("O livro requisitado não existe");
        }

        Book existentBookObject = existentBook.get();

        existentBookObject.setTitle(book.getTitle());
        existentBookObject.setAuthor(book.getAuthor());
        existentBookObject.setLaunchDate(book.getLaunchDate());

        return bookRepository.save(existentBookObject);
    }

    public Book rent(Book book) throws ClassNotFoundException {
        Optional<Book> existentBook = bookRepository.findById(book.getId());
        if(existentBook.isEmpty()){
            throw new ClassNotFoundException("O livro requisitado não existe");
        }

        Book existentBookObject = existentBook.get();

        existentBookObject.setLocatorId(book.getLocatorId());

        return bookRepository.save(existentBookObject);
    }

    public Book returnBook(Book book) throws ClassNotFoundException {
        Optional<Book> existentBook = bookRepository.findById(book.getId());
        if(existentBook.isEmpty()){
            throw new ClassNotFoundException("O livro requisitado não existe");
        }

        Book existentBookObject = existentBook.get();

        existentBookObject.setLocatorId(null);

        return bookRepository.save(existentBookObject);
    }

    public void deleteById(String id) throws ClassNotFoundException {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty()){
            throw new ClassNotFoundException("O livro requisitado não existe");
        }
        bookRepository.deleteById(id);
    }

}
