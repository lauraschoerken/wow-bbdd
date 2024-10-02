package com.wow_database.wow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wow_database.wow.global.file.FileManager;

@SpringBootApplication
public class WowApplication {

	public static void main(String[] args) {
		FileManager.loadTexts();
		SpringApplication.run(WowApplication.class, args);
	}

}
