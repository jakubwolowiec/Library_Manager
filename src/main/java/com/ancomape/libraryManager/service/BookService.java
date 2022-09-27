package com.ancomape.libraryManager.service;

import com.ancomape.libraryManager.exceptions.BookNotFoundException;
import com.ancomape.libraryManager.model.Book;
import com.ancomape.libraryManager.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public List<Book> findAllBooks(){
        return bookRepository.findAll();
    }

    public Book updateBook(Book book){
        return bookRepository.save(book);
    }

    public Book findBookById(Long id){
        return bookRepository.findBookById(id).orElseThrow(() -> new BookNotFoundException("Book by ID: " + id + " was not found!"));
    }

    public void deleteBookById(Long id){
        bookRepository.deleteBookById(id);
    }
}
