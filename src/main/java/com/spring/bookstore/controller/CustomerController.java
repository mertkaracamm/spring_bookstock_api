package com.spring.bookstore.controller;


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
import com.spring.bookstore.service.CustomerService;

import java.util.List;

@RequestMapping("customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    CustomerService userService;

    @GetMapping("/all")
    public List<Customer> findAllUser(@RequestParam("token") String token) throws AuthenticationFailException {
    	tokenService.authenticate(token);
        return customerRepository.findAll();
    }

    @PostMapping("/signup")
    public ResponseDto Signup(@RequestBody SignupDto signupDto) throws CustomException {
        return userService.signUp(signupDto);
    }

    //TODO token should be updated
    @PostMapping("/signIn")
    public SignInResponseDto Signup(@RequestBody SignInDto signInDto) throws CustomException {
        return userService.signIn(signInDto);
    }


}
