package com.spring.bookstore.dto;

import java.time.LocalDateTime;

public class BookResponseDto {
	private final boolean success;
	private final String message;
	// try again
	
	public BookResponseDto(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}
	
	public String getTimestamp() {
		return LocalDateTime.now().toString();
	}
}
