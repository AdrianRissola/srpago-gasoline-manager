package com.srpago.gasoline.manager.exception;

import org.springframework.http.HttpStatus;

import com.srpago.gasoline.manager.dto.ResponseDto;

public class FuelManagerException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;
	private ResponseDto responseDto;

	public FuelManagerException(String error, String message, HttpStatus httpStatus) {
		super(message);
		this.setHttpStatus(httpStatus);
		this.responseDto = new ResponseDto(false, error, message);
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public ResponseDto getResponseDto() {
		return responseDto;
	}




}
