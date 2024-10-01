package com.wow_database.wow.model.enums.checker;

import com.wow_database.wow.model.enums.Difficulty;

public class DifficultyChecker {

	private DifficultyChecker() {
	}

	public static String checkDifficulty(String input) {
		if (input == null || input.trim().isEmpty()) {
			return null;
		}

		StringBuilder foundDifficulties = new StringBuilder();
		String[] difficultiesInString = input.split(",\\s*|\\s+");

		for (String difficultyName : difficultiesInString) {
			try {
				Difficulty difficulty = Difficulty.valueOf(difficultyName.toUpperCase().trim());
				foundDifficulties.append(difficulty.name()).append(",");
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(difficultyName + " is a invalid difficulty", e);
			}
		}
		if (foundDifficulties.length() > 0) {
			foundDifficulties.setLength(foundDifficulties.length() - 1);
		}

		return foundDifficulties.toString(); // Retornar la cadena resultante
	}

}
