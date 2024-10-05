package com.wow_database.wow.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;

import com.wow_database.wow.global.file.FileManager;
import com.wow_database.wow.model.enums.checker.ExpansionChecker;

@SpringBootTest
public class ExpansionCheckerTest {

	@Test
	public void testValidExpansion() {
		String input = "   classic";
		String result = ExpansionChecker.checkExpansion(input);
		assertEquals("CLASSIC", result);
	}

	@Test
	public void testInvalidExpansion() {
		try (MockedStatic<FileManager> mockedStatic = mockStatic(FileManager.class)) {
			mockedStatic.when(() -> FileManager.getText("invalid.expansion")).thenReturn("Invalid expansion: %s");

			String input = "invalidExpansion";
			Exception exception = assertThrows(IllegalArgumentException.class, () -> {
				ExpansionChecker.checkExpansion(input);
			});
			assertEquals("Invalid expansion: invalidExpansion", exception.getMessage());
		}
	}

}
