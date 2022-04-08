package com.spring.bookstore.dto.book;


import javax.validation.constraints.NotNull;

import com.spring.bookstore.model.Book;



public class UpdateStockDto {

    private Integer id;
    private Integer stockQuantity;
    private double price;
    

    public UpdateStockDto(Integer id,Integer stockQuantity,double price) {
        this.id = id;
        this.stockQuantity = stockQuantity;
        this.price=price;
        
    }

    public UpdateStockDto() {
    }



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getStockQuantity() {
		return stockQuantity;
	}



	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}

  
	
    
}
