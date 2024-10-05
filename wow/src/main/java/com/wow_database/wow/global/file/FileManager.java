package com.wow_database.wow.global.file;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileManager {
	private static Properties texts = new Properties();

	public static void loadTexts(String language) {
		try {
			String file = language.equals("es") ? "texts_es.properties" : "texts_en.properties";
			InputStream input = FileManager.class.getClassLoader().getResourceAsStream(file);
			if (input == null) {
				System.out.println("File not found: " + file);
				return;
			}
			texts.load(input);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadTexts() {
		try {
			String file = "texts_en.properties";
			InputStream input = FileManager.class.getClassLoader().getResourceAsStream(file);
			if (input == null) {
				System.out.println("File not found: " + file);
				return;
			}
			texts.load(input);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getText(String key) {
		String value = texts.getProperty(key);
		if (value == null) {
			System.out.println("No se encontró valor para la clave: " + key);
		}
		return value;
	}

	private FileManager() {
	}
}
