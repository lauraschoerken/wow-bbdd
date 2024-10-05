package com.wow_database.wow.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wow_database.wow.global.file.FileManager;
import com.wow_database.wow.model.entity.Raid;
import com.wow_database.wow.service.RaidService;

public class RaidControllerTest {

	@Mock
	private RaidService raidService;

	@InjectMocks
	private RaidController raidController;

	private Raid testRaid;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this); // Inicializa los mocks

		// Inicializa el objeto de prueba
		testRaid = new Raid();
		testRaid.setId(1L);
		testRaid.setName("Test Raid");
		testRaid.setExpansion("CLASSIC");
		testRaid.setClasses("MAGE,WARRIOR");
		testRaid.setDifficulty("NORMAL");
		// Agrega otros atributos si es necesario
	}

	@Test
	void testGetAllRaids() {
		List<Raid> raids = Arrays.asList(testRaid);
		when(raidService.getAllRaids()).thenReturn(raids);
		List<Raid> response = raidController.getAllRaids();
		assertEquals(1, response.size());
		assertEquals("Test Raid", response.get(0).getName());
		verify(raidService, times(1)).getAllRaids();
	}

	@Test
	void testGetRaidById() {
		when(raidService.getRaidById(1L)).thenReturn(testRaid);
		Raid response = raidController.getRaidsById(1L);
		assertEquals("Test Raid", response.getName());
		verify(raidService, times(1)).getRaidById(1L);
	}

	@Test
	void testDeleteRaid() {
		when(raidService.getRaidById(1L)).thenReturn(testRaid);
		FileManager.loadTexts();
		ResponseEntity<String> response = raidController.deleteRaid(1L);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(raidService, times(1)).deleteRaidById(1L);
	}

	@Test
	void testNewRaid() {
		when(raidService.saveRaid(testRaid)).thenReturn(testRaid);
		ResponseEntity<Raid> response = raidController.newRaid(testRaid);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Test Raid", response.getBody().getName());
		verify(raidService, times(1)).saveRaid(testRaid);
	}

	@Test
	void testUpdateRaid() {
		when(raidService.getRaidById(1L)).thenReturn(testRaid);
		when(raidService.saveRaid(testRaid)).thenReturn(testRaid);
		ResponseEntity<String> response = raidController.updateRaid(1L, testRaid);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		verify(raidService, times(1)).saveRaid(testRaid);
	}

	// Aquí puedes agregar más pruebas según sea necesario
}
