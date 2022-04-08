package com.spring.bookstore.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.bookstore.dto.BookResponseDto;
import com.spring.bookstore.dto.ResponseDto;
import com.spring.bookstore.dto.book.BookDto;
import com.spring.bookstore.dto.book.UpdateStockDto;
import com.spring.bookstore.dto.customer.SignInDto;
import com.spring.bookstore.dto.customer.SignInResponseDto;
import com.spring.bookstore.dto.customer.SignupDto;
import com.spring.bookstore.exception.AuthenticationFailException;
import com.spring.bookstore.exception.CustomException;
import com.spring.bookstore.model.Customer;
import com.spring.bookstore.repository.BookRepository;
import com.spring.bookstore.repository.CustomerRepository;
import com.spring.bookstore.service.TokenService;
import com.spring.bookstore.service.BookService;
import com.spring.bookstore.service.BookStockService;
import com.spring.bookstore.service.CustomerService;

import java.util.List;

@RequestMapping("book")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BookStockController {

	protected transient static Logger logger = LoggerFactory.getLogger(BookStockController.class);
	
    @Autowired
    TokenService tokenService;

    @Autowired
    CustomerService customerService;
    
    @Autowired
    BookStockService bookStockService;
    
    @Autowired
    BookService bookService;
            

    @GetMapping("/allBookInStock")
    public ResponseEntity<List<BookDto>> getBookss() {
        List<BookDto> body = bookService.listBooks();
        return new ResponseEntity<List<BookDto>>(body, HttpStatus.OK);
    }
        
    @PostMapping("/addBook")
    public ResponseEntity<BookResponseDto> addBook(@RequestBody BookDto bookDto) {
           
    	bookService.addBook(bookDto);
        return new ResponseEntity<BookResponseDto>(new BookResponseDto(true, "Book has been added"), HttpStatus.CREATED);
    }
    
    @PostMapping("/updateStock")
    public ResponseEntity<BookResponseDto> updateStock(@RequestBody UpdateStockDto updateStockDto) throws Exception {
           
    	boolean result=bookStockService.updateStockPriceDto(updateStockDto);
    	if (result) {
    		return new ResponseEntity<BookResponseDto>(new BookResponseDto(true, "Book has been updated"), HttpStatus.CREATED);
    	}
    	else {
    		return new ResponseEntity<BookResponseDto>(new BookResponseDto(false, "Fail Book has not been added"), HttpStatus.CREATED);
    	}
        
    }
           

}
