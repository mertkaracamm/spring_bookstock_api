package com.spring.bookstore.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookstore.dto.book.BookDto;
import com.spring.bookstore.exception.BookNotExistException;
import com.spring.bookstore.model.Book;
import com.spring.bookstore.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<BookDto> listBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> booktDtos = new ArrayList<>();
        for(Book product : books) {
        	BookDto bookDto = getDtoFromBook(product);
        	booktDtos.add(bookDto);
        }
        return booktDtos;
    }

    public static BookDto getDtoFromBook(Book book) {
    	BookDto bookDto = new BookDto(book);
        return bookDto;
    }

    public static Book getBookFromDto(BookDto bookDto) {
    	Book book = new Book(bookDto);
        return book;
    }

    public void addBook(BookDto productDto) {
        Book book = getBookFromDto(productDto);
        bookRepository.save(book);
    }

    public void updateBook(Integer bookID, BookDto bookDto) {
    	Book book = getBookFromDto(bookDto);
    	book.setId(bookID);
    	bookRepository.save(book);
    }


    public Book getBookById(Integer bookId) throws BookNotExistException {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (!optionalBook.isPresent())
            throw new BookNotExistException("Book id is invalid " + bookId);
        return optionalBook.get();
    }


}
