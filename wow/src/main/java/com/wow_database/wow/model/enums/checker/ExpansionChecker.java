package com.wow_database.wow.model.enums.checker;

import com.wow_database.wow.model.enums.Expansion;

public class ExpansionChecker {

	private ExpansionChecker() {
	}

	public static String checkExpansion(String input) {
		if (input == null || input.trim().isEmpty()) {
			return null;
		}
		try {
			Expansion expansion = Expansion.valueOf(input.toUpperCase().trim());
			return expansion.name(); // Retornar el nombre de la expansión
		} catch (IllegalArgumentException e) {

			throw new IllegalArgumentException(input + " is a invalid expansion", e);
		}
	}

}
