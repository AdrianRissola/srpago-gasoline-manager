package com.srpago.gasoline.manager.dto;

public class ResponseDto {

    private Boolean success;

    private String error;

    private String message;
    
    public ResponseDto() {}

    public ResponseDto(Boolean success, String message) {
		this.setSuccess(success);
		this.setMessage(message);
	}

	public ResponseDto(Boolean success, String error, String message) {
		this.setSuccess(success);
		this.setError(error);
		this.setMessage(message);
	}

	public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    

}