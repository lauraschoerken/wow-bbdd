package com.wow_database.wow.model.enums.checker;

import com.wow_database.wow.global.file.FileManager;
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
			return expansion.name();
		} catch (IllegalArgumentException e) {

			String errorMessage = String.format(FileManager.getText("invalid.expansion"), input);
			throw new IllegalArgumentException(errorMessage, e);
		}
	}

}
