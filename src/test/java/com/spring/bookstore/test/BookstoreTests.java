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
import com.spring.bookstore.dto.customer.SignInDto;
import com.spring.bookstore.dto.customer.SignInResponseDto;
import com.spring.bookstore.model.Customer;


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
		
		customer.setFirstName("Mert");
		customer.setLastName("Karaçam");
		customer.setEmail("mert3.karacamm@gmail.com");
		customer.setPassword("mert_123");
		
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
		
		signInDto.setEmail("Mert");
		signInDto.setPassword("mert_123");
		
			
		/*signInDto.setEmail("Mert");
		signInDto.setPassword("mert_124");*/ //şifre kontrolü
		
		ResponseEntity<SignInResponseDto> postResponse = restTemplate.postForEntity(getRootUrl() + "/customer/signIn", signInDto, SignInResponseDto.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	/*@Test
	public void testUpdateEmployee() {
		int id = 1;
		Employee employee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
		employee.setFirstName("admin1");
		employee.setLastName("admin2");

		restTemplate.put(getRootUrl() + "/employees/" + id, employee);

		Employee updatedEmployee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
		assertNotNull(updatedEmployee);
	}

	@Test
	public void testDeleteEmployee() {
		int id = 2;
		Employee employee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
		assertNotNull(employee);

		restTemplate.delete(getRootUrl() + "/employees/" + id);

		try {
			employee = restTemplate.getForObject(getRootUrl() + "/employees/" + id, Employee.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}*/
}
