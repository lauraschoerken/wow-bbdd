package com.wow_database.wow.model.enums.checker;

import com.wow_database.wow.model.enums.ClassName;

public class ClassNameChecker {

	private ClassNameChecker() {
	}

	public static String checkClassName(String input) {
		if (input == null || input.trim().isEmpty()) {
			return null;
		}
		StringBuilder foundClasses = new StringBuilder();
		String[] classesInString = input.split(",\\s*|\\s+");

		for (String className : classesInString) {
			try {
				ClassName characterClass = ClassName.valueOf(className.toUpperCase().trim());
				foundClasses.append(characterClass.name()).append(",");
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException(className + " no es una clase válida.", e);
			}
		}
		if (foundClasses.length() > 0) {
			foundClasses.setLength(foundClasses.length() - 1);
		}
		return foundClasses.toString();
	}

}
