package com.wow_database.wow.global.constant;

public final class Constants {
	// Evita que se instancie la clase
	private Constants() {
		throw new UnsupportedOperationException("Esta clase no debe ser instanciada.");
	}

	public static final String ERROR_CODE_JSON_PARSE_ERROR = "JSON_PARSE_ERROR";
	public static final String ERROR_CODE_INVALID_ARGUMENT = "INVALID_ARGUMENT";
	public static final String ERROR_CODE_GENERIC_ERROR = "GENERIC_ERROR";

}
