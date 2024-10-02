package com.wow_database.wow.model.enums.checker;

import com.wow_database.wow.model.enums.Difficulty;

public class DifficultyChecker {

	private DifficultyChecker() {
	}

	public static String checkDifficulty(String input) {
		return GeneralChecker.checkEnum(input, Difficulty.class);
	}

}
