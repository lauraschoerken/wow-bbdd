package com.wow_database.wow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.wow_database.wow.global.file.FileManager;

@SpringBootTest
class WowApplicationTests {

	@Test
	void contextLoads() {
		FileManager.loadTexts();

	}

}
