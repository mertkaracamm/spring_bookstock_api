package com.spring.bookstore.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.spring.bookstore.dto.ResponseDto;
import com.spring.bookstore.dto.customer.SignInDto;
import com.spring.bookstore.dto.customer.SignInResponseDto;
import com.spring.bookstore.dto.customer.SignupDto;
import com.spring.bookstore.exception.AuthenticationFailException;
import com.spring.bookstore.exception.CustomException;
import com.spring.bookstore.model.Customer;
import com.spring.bookstore.repository.CustomerRepository;
import com.spring.bookstore.service.TokenService;
import com.spring.bookstore.service.BookStockService;
import com.spring.bookstore.service.CustomerService;

import java.util.List;

@RequestMapping("customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CustomerController {

	protected transient static Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    CustomerService customerService;
       

    @GetMapping("/allCustomer")
    public List<Customer> findAllUser(@RequestParam("token") String token) throws AuthenticationFailException {
    	tokenService.authenticate(token);
        return customerRepository.findAll();
    }

    @PostMapping("/signup")
    public ResponseDto SignUp(@RequestBody SignupDto signupDto) throws CustomException {
        return customerService.signUp(signupDto);
    }

    
    @PostMapping("/signIn")
    public SignInResponseDto SignIn(@RequestBody SignInDto signInDto) throws CustomException {
        return customerService.signIn(signInDto);
    }


}
