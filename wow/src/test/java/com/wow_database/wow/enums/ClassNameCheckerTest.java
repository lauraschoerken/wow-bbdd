package com.wow_database.wow.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;

import com.wow_database.wow.global.file.FileManager;
import com.wow_database.wow.model.enums.checker.ClassNameChecker;

@SpringBootTest
public class ClassNameCheckerTest {

	@Test
	public void testValidClassName() {
		String input = "mage, warrior";
		String result = ClassNameChecker.checkClassName(input);
		assertEquals("MAGE,WARRIOR", result);
	}

	@Test
	public void testInvalidClassName() {
		try (MockedStatic<FileManager> mockedStatic = mockStatic(FileManager.class)) {
			mockedStatic.when(() -> FileManager.getText("invalid.classname")).thenReturn("Invalid class: %s");

			String input = "invalidClass";
			Exception exception = assertThrows(IllegalArgumentException.class, () -> {
				ClassNameChecker.checkClassName(input);
			});
			assertEquals("Invalid class: invalidClass", exception.getMessage());
		}
	}

}
