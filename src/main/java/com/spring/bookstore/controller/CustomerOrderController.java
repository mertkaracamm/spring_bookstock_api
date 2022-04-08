package com.spring.bookstore.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.bookstore.dto.BookResponseDto;
import com.spring.bookstore.dto.ResponseDto;
import com.spring.bookstore.dto.book.BookDetailForOrderDto;
import com.spring.bookstore.dto.book.BookDto;
import com.spring.bookstore.dto.book.BookOrderDto;
import com.spring.bookstore.dto.book.UpdateStockDto;
import com.spring.bookstore.dto.customer.SignInDto;
import com.spring.bookstore.dto.customer.SignInResponseDto;
import com.spring.bookstore.dto.customer.SignupDto;
import com.spring.bookstore.exception.AuthenticationFailException;
import com.spring.bookstore.exception.CustomException;
import com.spring.bookstore.exception.OrderNotFoundException;
import com.spring.bookstore.model.Book;
import com.spring.bookstore.model.Customer;
import com.spring.bookstore.model.CustomerOrder;
import com.spring.bookstore.repository.BookRepository;
import com.spring.bookstore.repository.BookStockRepository;
import com.spring.bookstore.repository.CustomerRepository;
import com.spring.bookstore.service.TokenService;
import com.spring.bookstore.service.BookService;
import com.spring.bookstore.service.BookStockService;
import com.spring.bookstore.service.CustomerOrderService;
import com.spring.bookstore.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("order")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CustomerOrderController {

	protected transient static Logger logger = LoggerFactory.getLogger(CustomerOrderController.class);
	
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
            throws Exception {
        
    	try {
    	// validate token
    	tokenService.authenticate(token);
        
    	}
    	catch (AuthenticationFailException ex) {
    		logger.error("addOrder aouhentication hatası", ex);
    	}
    	
    	// get user
        Customer user = tokenService.getCustomer(token);
        // getBook
        Book book=bookService.getBookById(bookId.getBookId());
        
        //checkStock
        int stockCount=bookStockService.checkBookStock(bookId.getBookId());
        
        if (stockCount>0) {
        	customerOrderService.placeOrder(user, book);
        	bookStockService.updateStockFromOrder(stockCount-1, bookId.getBookId()); // update stockRecords after customer order
        	
        	return new ResponseEntity<>(new BookResponseDto(true, "Order has been placed."), HttpStatus.CREATED);
        	        	        
        }
        else {
        	return new ResponseEntity<>(new BookResponseDto(false, "Order has not been placed. Because there is no book in the stocks!"), HttpStatus.CREATED);
        }
        
               
        
    }
    
    
    // get all orders
    @GetMapping("/allOrders")
    public ResponseEntity<List<CustomerOrder>> getAllOrders(@RequestParam("token") String token) throws AuthenticationFailException {
        // validate token
    	tokenService.authenticate(token);
        // retrieve user
        Customer customer = tokenService.getCustomer(token);
        // get orders
        List<CustomerOrder> orderDtoList = customerOrderService.listOrders(customer);

        return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
    }
    
    // get bookDetail for an order
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOrderById(@PathVariable("id") Integer id, @RequestParam("token") String token)
            throws Exception {
        
    	try {
        	// validate token
        	tokenService.authenticate(token);
            
        	}
        	catch (AuthenticationFailException ex) {
        		logger.error("addOrder aouhentication hatası", ex);
        	}
    	
    	try {
        	
        	List<BookDetailForOrderDto> listBookOrderDetail= new ArrayList<>();
        	
        	listBookOrderDetail=bookStockService.getBookDetailForOrder(id);
        	
            CustomerOrder order = customerOrderService.getOrder(id);
            return new ResponseEntity<>(listBookOrderDetail,HttpStatus.OK);
        }
        catch (OrderNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }

    }
    
    
           

}
