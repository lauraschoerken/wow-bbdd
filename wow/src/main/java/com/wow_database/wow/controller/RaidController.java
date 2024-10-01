package com.wow_database.wow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wow_database.wow.model.entity.Raid;
import com.wow_database.wow.model.enums.checker.ClassNameChecker;
import com.wow_database.wow.model.enums.checker.DifficultyChecker;
import com.wow_database.wow.model.enums.checker.ExpansionChecker;
import com.wow_database.wow.service.RaidService;

@RestController
@RequestMapping("api/v1/raids")
public class RaidController {

	@Autowired
	private RaidService raidService;

	@GetMapping
	public String getRaidByName(@RequestParam String raidName) {
		return "Fetching details for raid: " + raidName;
	}

	@GetMapping("/all")
	public String getAllRaids() {
		return "Fetching all raids from the database" + raidService.getAllRaids();
	}

	@GetMapping("/id/{id}")
	public String getRaidsById(@PathVariable Long id) {
		return "Fetching raid with id: " + id;
	}

	@GetMapping("/class/{className}")
	public List<Raid> getRaidsByClass(@PathVariable String className) {
		return raidService.getRaidsByClass(className);
	}

	@GetMapping("/difficulty/{difficulty}")
	public List<Raid> getRaidsByDifficulty(@PathVariable String difficulty) {
		return raidService.getRaidsByDifficulty(difficulty);
	}

	@GetMapping("/expansion/{expansion}")
	public List<Raid> getRaidsByExpansion(@PathVariable String expansion) {
		return raidService.getRaidsByExpansion(expansion);
	}

	@PostMapping
	public ResponseEntity<String> newRaid(@RequestBody Raid raid) {
		raid.setDifficulty(DifficultyChecker.checkDifficulty(raid.getDifficulty()));
		raid.setClasses(ClassNameChecker.checkClassName(raid.getClasses()));
		raid.setExpansion(ExpansionChecker.checkExpansion(raid.getExpansion()));

		raidService.saveRaid(raid);
		return ResponseEntity.ok("Raid creada con éxito con ID: " + raid.getId());
	}

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello, welcome to the WoW Raids API!";
	}

}
