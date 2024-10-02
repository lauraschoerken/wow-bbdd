package com.wow_database.wow.model.enums.checker;

import com.wow_database.wow.model.enums.ClassName;

public class ClassNameChecker {

	private ClassNameChecker() {
	}

	public static String checkClassName(String input) {
		return GeneralChecker.checkEnum(input, ClassName.class);

	}

}
