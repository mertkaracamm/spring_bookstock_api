package com.spring.bookstore.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.bookstore.model.Token;
import com.spring.bookstore.model.Customer;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {
    Token findTokenByCustomer(Customer customer);
    Token findTokenByToken(String token);
}
