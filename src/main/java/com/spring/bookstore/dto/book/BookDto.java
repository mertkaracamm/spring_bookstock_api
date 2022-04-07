package com.spring.bookstore.dto.book;


import javax.validation.constraints.NotNull;

import com.spring.bookstore.model.Book;



public class BookDto {

    private Integer id;
    private @NotNull String title;
    private @NotNull String author;
    private @NotNull double price;
    private @NotNull String description;
    private @NotNull int stockQuantity;
    
    public BookDto(Book book) {
        this.setId(book.getId());
        this.setTitle(book.getTitle());
        this.setAuthor(book.getAuthor());
        this.setPrice(book.getPrice());
        this.setDescription(book.getDescription());
        this.setStockQuantity(book.getStockQuantity());
    }
    

    public BookDto(@NotNull String title, @NotNull String author, @NotNull double price, @NotNull String description,int stockQuantity) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.description = description;
        this.stockQuantity= stockQuantity;     
    }

    public BookDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public int getStockQuantity() {
		return stockQuantity;
	}


	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}


	
    
}
