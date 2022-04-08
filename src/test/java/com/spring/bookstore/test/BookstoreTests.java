package com.spring.bookstore.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.spring.bookstore.BookstoreApplication;
import com.spring.bookstore.dto.BookResponseDto;
import com.spring.bookstore.dto.book.BookDto;
import com.spring.bookstore.dto.book.BookOrderDto;
import com.spring.bookstore.dto.customer.SignInDto;
import com.spring.bookstore.dto.customer.SignInResponseDto;
import com.spring.bookstore.model.Customer;
import com.spring.bookstore.model.CustomerOrder;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookstoreApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookstoreTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testCreateSignUp() {
		Customer customer = new Customer();
		
		customer.setFirstName("Ali");
		customer.setLastName("Veli");
		customer.setEmail("ali.veli@gmail.com");
		customer.setPassword("ali_123");
		
		/*customer.setFirstName("Ali");
		customer.setLastName("Veli");
		customer.setEmail("ali.veli@gmail.com");
		customer.setPassword("ALİ_123");*/
		
		ResponseEntity<Customer> postResponse = restTemplate.postForEntity(getRootUrl() + "/customer/signup", customer, Customer.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}
	
	@Test
	public void testCreateSignin() {
		SignInDto signInDto = new SignInDto();
		
		signInDto.setEmail("ali.veli@gmail.com");
		signInDto.setPassword("ali_123");
		
			
		/*signInDto.setEmail("ali.veli@gmail.com");
		signInDto.setPassword("ali_124");*/ //şifre kontrolü
		
		ResponseEntity<SignInResponseDto> postResponse = restTemplate.postForEntity(getRootUrl() + "/customer/signIn", signInDto, SignInResponseDto.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}
	
	@Test
	public void testCreateAddBook() {
		
		BookDto bookDto = new BookDto();
		
		bookDto.setTitle("Dublörün Dilemması");
		bookDto.setAuthor("Murat Menteş");
		bookDto.setPrice(30.00);
		bookDto.setDescription("Dublörün Dilemması");
		bookDto.setStockQuantity(2);
		
		
		ResponseEntity<BookResponseDto> postResponse = restTemplate.postForEntity(getRootUrl() + "/book/addBook", bookDto, BookResponseDto.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}
	
	@Test
	public void testCreateAddOrder() {
		
		BookOrderDto bookId= new BookOrderDto();
		String token ="044d2fb6-c4c2-4e8f-9d64-31ed9d413a29";
		
		
		bookId.setBookId(2);
					
		
		ResponseEntity<BookResponseDto> getResponse = restTemplate.getForEntity(getRootUrl() + "/order/addOrder?token="+token, BookResponseDto.class);								
		assertNotNull(getResponse);
		assertNotNull(getResponse.getBody());
	}
	
	@Test
	public void testCreateAllOrder() {
		
		
		String token ="044d2fb6-c4c2-4e8f-9d64-31ed9d413a29";
						
		
		
		ResponseEntity<CustomerOrder> getResponse = restTemplate.getForEntity(getRootUrl() + "/order/allOrders?token=\"+token", CustomerOrder.class);
				
		assertNotNull(getResponse);
		assertNotNull(getResponse.getBody());
	}

	
}
