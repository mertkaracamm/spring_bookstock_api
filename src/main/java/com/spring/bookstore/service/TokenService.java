package com.spring.bookstore.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.spring.bookstore.model.Token;
import com.spring.bookstore.config.MessageStrings;
import com.spring.bookstore.exception.AuthenticationFailException;
import com.spring.bookstore.model.Customer;
import com.spring.bookstore.repository.TokenRepository;
import com.spring.bookstore.utils.Helper;

@Service
public class TokenService {

    @Autowired
    TokenRepository repository;

    public void saveConfirmationToken(Token authenticationToken) {
        repository.save(authenticationToken);
    }

    public Token getToken(Customer customer) {
        return repository.findTokenByCustomer(customer);
    }

    public Customer getCustomer(String token) {
        Token authenticationToken = repository.findTokenByToken(token);
        if (Helper.notNull(authenticationToken)) {
            if (Helper.notNull(authenticationToken.getCustomer())) {
                return authenticationToken.getCustomer();
            }
        }
        return null;
    }

    public void authenticate(String token) throws AuthenticationFailException {
        if (!Helper.notNull(token)) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_PRESENT);
        }
        if (!Helper.notNull(getCustomer(token))) {
            throw new AuthenticationFailException(MessageStrings.AUTH_TOEKN_NOT_VALID);
        }
    }
}
