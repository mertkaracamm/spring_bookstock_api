package com.spring.bookstore.dto.book;


import javax.validation.constraints.NotNull;

import com.spring.bookstore.model.Book;



public class BookOrderDto {

    private Integer bookId;
    
    

    public BookOrderDto(Integer bookId) {
        this.bookId = bookId;
        
    }

    public BookOrderDto() {
    }

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}



	
    
}
