package com.wow_database.wow.model.enums.checker;

import com.wow_database.wow.global.file.FileManager;

public class GeneralChecker {

	public static <E extends Enum<E>> String checkEnum(String input, Class<E> enumClass) {
		if (input == null || input.trim().isEmpty()) {
			return null;
		}

		StringBuilder foundValues = new StringBuilder();
		String[] valuesInString = input.split(",\\s*|\\s+");

		for (String valueName : valuesInString) {
			try {
				E enumValue = Enum.valueOf(enumClass, valueName.toUpperCase().trim());
				foundValues.append(enumValue.name()).append(",");
			} catch (IllegalArgumentException e) {
				String textFileKey = "invalid." + enumClass.getSimpleName().toLowerCase();
				String errorMessage = String.format(FileManager.getText(textFileKey), valueName);
				throw new IllegalArgumentException(errorMessage, e);

			}
		}

		if (foundValues.length() > 0) {
			foundValues.setLength(foundValues.length() - 1);
		}
		return foundValues.toString();
	}

}
