package com.wow_database.wow.global.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wow_database.wow.global.constant.Constants;
import com.wow_database.wow.global.file.FileManager;
import com.wow_database.wow.model.enums.ClassName;
import com.wow_database.wow.model.enums.Difficulty;
import com.wow_database.wow.model.enums.Expansion;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<CustomErrorResponse> handleHttpMessageNotReadableException(
			HttpMessageNotReadableException e) {
		String errorMessage = e.getMessage();
		String errorCode = Constants.ERROR_CODE_JSON_PARSE_ERROR;

		CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage,
				errorCode, null);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<CustomErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
		String errorMessage = e.getMessage();
		String errorCode = Constants.ERROR_CODE_INVALID_ARGUMENT;

		StringBuilder options = new StringBuilder();

		if (e.getMessage().contains(FileManager.getText("expansion"))) {
			for (Expansion exp : Expansion.values()) {
				options.append(exp.name()).append(", ");
			}
		} else if (e.getMessage().contains(FileManager.getText("class"))) {
			for (ClassName cls : ClassName.values()) {
				options.append(cls.name()).append(", ");
			}
		} else if (e.getMessage().contains(FileManager.getText("difficulty"))) {
			for (Difficulty difficulty : Difficulty.values()) {
				options.append(difficulty.name()).append(", ");
			}
		} else {
			options.append(FileManager.getText("options.not.available"));
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
		String errorMessage = e.getMessage();
		String errorCode = Constants.ERROR_CODE_GENERIC_ERROR;

		CustomErrorResponse errorResponse = new CustomErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				errorMessage, errorCode, null);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
}
