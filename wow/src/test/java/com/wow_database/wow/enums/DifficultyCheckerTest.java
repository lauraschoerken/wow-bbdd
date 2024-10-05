package com.wow_database.wow.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;

import com.wow_database.wow.global.file.FileManager;
import com.wow_database.wow.model.enums.checker.DifficultyChecker;

@SpringBootTest
public class DifficultyCheckerTest {

	@Test
	public void testValidDifficulty() {
		String input = "NOrmAL,    MYTHIC";
		String result = DifficultyChecker.checkDifficulty(input);
		assertEquals("NORMAL,MYTHIC", result);
	}

	@Test
	public void testInvalidDifficulty() {
		try (MockedStatic<FileManager> mockedStatic = mockStatic(FileManager.class)) {
			mockedStatic.when(() -> FileManager.getText("invalid.difficulty")).thenReturn("Invalid difficulty: %s");

			String input = "invalidDifficulty";
			Exception exception = assertThrows(IllegalArgumentException.class, () -> {
				DifficultyChecker.checkDifficulty(input);
			});
			assertEquals("Invalid difficulty: invalidDifficulty", exception.getMessage());
		}
	}

}
