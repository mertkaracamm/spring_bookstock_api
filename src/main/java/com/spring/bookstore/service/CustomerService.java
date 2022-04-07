package com.spring.bookstore.service;



import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bookstore.config.MessageStrings;
import com.spring.bookstore.dto.ResponseDto;
import com.spring.bookstore.dto.customer.SignInDto;
import com.spring.bookstore.dto.customer.SignInResponseDto;
import com.spring.bookstore.dto.customer.SignupDto;
import com.spring.bookstore.enums.ResponseStatus;
import com.spring.bookstore.exception.AuthenticationFailException;
import com.spring.bookstore.exception.CustomException;
import com.spring.bookstore.model.Customer;
import com.spring.bookstore.model.Token;
import com.spring.bookstore.repository.BookStockRepository;
import com.spring.bookstore.repository.CustomerRepository;
import com.spring.bookstore.utils.Helper;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



@Service
public class CustomerService {

	protected static String initVector = "RandomInitVector"; 
	protected static String key = "125Bookstore1265";
	
    @Autowired
    CustomerRepository userRepository;

    @Autowired
    TokenService authenticationService;
    
    @Autowired
    BookStockRepository bookStockRepository;

    Logger logger = LoggerFactory.getLogger(CustomerService.class);


    public ResponseDto signUp(SignupDto signupDto)  throws CustomException {
        
    	    	
    	// Check to see if the current email address has already been registered.
        if (Helper.notNull(userRepository.findByEmail(signupDto.getEmail()))) { // 
            // If the email address has been registered then throw an exception.
            throw new CustomException("User already exists");
        }
        // first encrypt the password
        String encryptedPassword = signupDto.getPassword();
        try {
        	
            encryptedPassword = encryptPassword(key, initVector, signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
        }


        Customer user = new Customer(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(), encryptedPassword );

        Customer createdUser;
        try {
            // save the User
            createdUser = userRepository.save(user);
            // generate token for user
            final Token authenticationToken = new Token(createdUser);
            // save token in database
            authenticationService.saveConfirmationToken(authenticationToken);
            // success in creating
            return new ResponseDto(ResponseStatus.success.toString(), MessageStrings.USER_CREATED);
        } catch (Exception e) {
            // handle signup error
            throw new CustomException(e.getMessage());
        }
    }

    public SignInResponseDto signIn(SignInDto signInDto) throws CustomException {
        // first find User by email
        Customer user = userRepository.findByEmail(signInDto.getEmail());
        if(!Helper.notNull(user)){
            throw  new AuthenticationFailException("user not present");
        }
        try {
            // check if password is right
            if (!user.getPassword().equals(encryptPassword(key, initVector, signInDto.getPassword()))){ 
                // passowrd doesnot match
                throw  new AuthenticationFailException(MessageStrings.WRONG_PASSWORD);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.error("hashing password failed {}", e.getMessage());
            throw new CustomException(e.getMessage());
        }

        Token token = authenticationService.getToken(user);

        if(!Helper.notNull(token)) {
            // token not present
            throw new CustomException("token not present");
        }

        return new SignInResponseDto ("success", token.getToken());
    }

    
   String encryptPassword(String key, String initVector, String value) throws NoSuchAlgorithmException {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    String decryptPassword(String key, String initVector, String encrypted) throws NoSuchAlgorithmException {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
  
}
