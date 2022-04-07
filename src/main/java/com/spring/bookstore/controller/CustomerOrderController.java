package com.spring.bookstore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.bookstore.dto.BookResponseDto;
import com.spring.bookstore.dto.ResponseDto;
import com.spring.bookstore.dto.book.BookDto;
import com.spring.bookstore.dto.book.BookOrderDto;
import com.spring.bookstore.dto.book.UpdateStockDto;
import com.spring.bookstore.dto.customer.SignInDto;
import com.spring.bookstore.dto.customer.SignInResponseDto;
import com.spring.bookstore.dto.customer.SignupDto;
import com.spring.bookstore.exception.AuthenticationFailException;
import com.spring.bookstore.exception.CustomException;
import com.spring.bookstore.model.Book;
import com.spring.bookstore.model.Customer;
import com.spring.bookstore.repository.BookRepository;
import com.spring.bookstore.repository.CustomerRepository;
import com.spring.bookstore.service.TokenService;
import com.spring.bookstore.service.BookService;
import com.spring.bookstore.service.BookStockService;
import com.spring.bookstore.service.CustomerOrderService;
import com.spring.bookstore.service.CustomerService;

import java.util.List;

@RequestMapping("order")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CustomerOrderController {

    
    @Autowired
    TokenService tokenService;

    @Autowired
    CustomerService customerService;
    
    @Autowired
    BookStockService bookStockService;
    
    @Autowired
    BookService bookService;
    
    @Autowired
    CustomerOrderService customerOrderService;
            

    @PostMapping("/addOrder")
    public ResponseEntity<BookResponseDto> addOrder(@RequestParam("token") String token, @RequestBody BookOrderDto bookId)
            throws AuthenticationFailException {
        // validate token
    	tokenService.authenticate(token);
        // retrieve user
        Customer user = tokenService.getUser(token);
        
        // getBook
        Book book=bookService.getBookById(bookId.getBookId());
        
        // place the order
        customerOrderService.placeOrder(user, book);
        return new ResponseEntity<>(new BookResponseDto(true, "Order has been placed"), HttpStatus.CREATED);
    }
           

}
