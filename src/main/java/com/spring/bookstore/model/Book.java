package com.spring.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.bookstore.dto.book.BookDto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private @NotNull String title;
    private @NotNull String author;
    private @NotNull double price;
    private @NotNull String description;
    private @NotNull int stockQuantity;
   

    public Book(BookDto bookDto) {
        this.title = bookDto.getTitle();
        this.author = bookDto.getAuthor();
        this.price = bookDto.getPrice();
        this.description = bookDto.getDescription(); 
        this.stockQuantity=bookDto.getStockQuantity();
    }

    public Book(String title, String author, double price, String description,int stockQuantity) {
        super();
        this.title = title;
        this.author = author;
        this.price = price;
        this.description = description;
        this.stockQuantity=stockQuantity;
    }

    public Book() {
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

	@Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", stockQuantity='" + stockQuantity + '\'' +
                '}';
    }
}
