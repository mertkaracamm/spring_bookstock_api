package com.spring.bookstore.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.spring.bookstore.model.Token;
import com.spring.bookstore.config.MessageStrings;
import com.spring.bookstore.dto.book.BookDetailForOrderDto;
import com.spring.bookstore.dto.book.UpdateStockDto;
import com.spring.bookstore.exception.AuthenticationFailException;
import com.spring.bookstore.model.Customer;
import com.spring.bookstore.repository.BookStockRepository;
import com.spring.bookstore.repository.CustomerRepository;
import com.spring.bookstore.repository.TokenRepository;
import com.spring.bookstore.utils.Helper;

@Service
public class BookStockService {

	@Autowired
	BookStockRepository bookStockRepository;
	
	
	
	 public boolean updateStockPriceDto(UpdateStockDto updateStockDto) throws Exception {
		 
		return bookStockRepository.updateStockPriceDto(updateStockDto);
	 }
	 
	 public int checkBookStock(int bookId) throws Exception {
		 
		 return bookStockRepository.checkBookStock(bookId);
	 }
	 
	 public boolean updateStockFromOrder(int stock_quantity, int bookId) throws Exception {
		 
		 return bookStockRepository.updateStockFromOrder(stock_quantity, bookId);
	 }
	 
	 public List<BookDetailForOrderDto> getBookDetailForOrder(int orderId) throws Exception {
		 
		 return bookStockRepository.getBookDetailForOrder(orderId);
	 }
}
