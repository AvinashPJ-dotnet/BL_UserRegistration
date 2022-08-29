package com.bridgelab.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ResponseDTO {
	private Object data;
	private String message;
	private int statusCode;
	
	   public ResponseDTO(Object data, String message, int statusCode) {
		this.data = data;
		this.message = message;
		this.statusCode = statusCode;
	}

	   
	   
//	private final String token;
//	   public JwtResponseModel(String token) {
//	      this.token = token;
//	   }
//	   public String getToken() {
//	      return token;
//	   }
}
