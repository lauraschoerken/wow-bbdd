package com.wow_database.wow.global.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {
	private int status;
	private String message;
	private String errorCode;
	private String options;
}
