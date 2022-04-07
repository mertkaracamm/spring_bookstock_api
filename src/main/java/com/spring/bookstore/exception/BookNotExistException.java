package com.spring.bookstore.exception;

public class BookNotExistException extends IllegalArgumentException {
    public BookNotExistException(String msg) {
        super(msg);
    }
}
