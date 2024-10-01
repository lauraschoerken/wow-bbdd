package com.wow_database.wow.global.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	// Manejador para errores de Jackson (JSON no válido)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<CustomErrorResponse> handleHttpMessageNotReadableException(
			HttpMessageNotReadableException e) {
		// Mensaje y código de error personalizado
		String errorMessage = "Error: " + e.getMessage();
		String errorCode = "JSON_PARSE_ERROR"; // Código de error específico

		CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage,
				errorCode);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	// Manejo de IllegalArgumentException (u otra excepción específica)
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<CustomErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
		String errorMessage = "Error: " + e.getMessage();
		String errorCode = "INVALID_ARGUMENT"; // Código de error para argumentos inválidos

		CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage,
				errorCode);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	// Manejo de excepción general (Exception)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomErrorResponse> handleGeneralException(Exception e) {
		String errorMessage = "Error: " + e.getMessage();
		String errorCode = "GENERIC_ERROR"; // Código de error genérico

		CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				errorMessage, errorCode);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
}
