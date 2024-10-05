package com.wow_database.wow.global.handler.exception;

public class RaidNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RaidNotFoundException(String message) {
		super(message);
	}
}
