package com.wow_database.wow.global.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wow_database.wow.model.enums.ClassName;
import com.wow_database.wow.model.enums.Difficulty;
import com.wow_database.wow.model.enums.Expansion;

@ControllerAdvice
public class GlobalExceptionHandler {
	private String error = "Error: ";

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<CustomErrorResponse> handleHttpMessageNotReadableException(
			HttpMessageNotReadableException e) {
		String errorMessage = error + e.getMessage();
		String errorCode = "JSON_PARSE_ERROR";

		CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage,
				errorCode, null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<CustomErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
		String errorMessage = error + e.getMessage();
		String errorCode = "INVALID_ARGUMENT";

		StringBuilder options = new StringBuilder();

		if (e.getMessage().contains("expansion")) {
			for (Expansion exp : Expansion.values()) {
				options.append(exp.name()).append(", ");
			}
		} else if (e.getMessage().contains("class")) {
			for (ClassName cls : ClassName.values()) {
				options.append(cls.name()).append(", ");
			}
		} else if (e.getMessage().contains("difficulty")) {
			for (Difficulty difficulty : Difficulty.values()) {
				options.append(difficulty.name()).append(", ");
			}
		} else {
			options.append("No hay opciones disponibles.");
		}

		if (options.length() > 0) {
			options.setLength(options.length() - 2);
		}
		CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage,
				errorCode, options.toString());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomErrorResponse> handleGeneralException(Exception e) {
		String errorMessage = error + e.getMessage();
		String errorCode = "GENERIC_ERROR";

		CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				errorMessage, errorCode, null);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
}
