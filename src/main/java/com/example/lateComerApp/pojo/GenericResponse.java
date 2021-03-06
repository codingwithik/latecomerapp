package com.example.lateComerApp.pojo;

import lombok.Data;

public @Data class GenericResponse {

	private String message;
	private String code;
	private boolean success;

	public GenericResponse(String message) {
		super();
		this.message = message;
	}

	public GenericResponse(String message, boolean success, String error) {
		super();
		this.message = message;
		this.success = success;
		this.code = error;
	}
}
