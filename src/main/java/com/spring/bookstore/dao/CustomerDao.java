package com.spring.bookstore.dao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CustomerDao {
	public static void main(String[] args) {
		SpringApplication.run(CustomerDao.class, args);
	}
}
