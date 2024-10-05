package com.wow_database.wow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wow_database.wow.global.file.FileManager;
import com.wow_database.wow.model.dto.RaidDTO;
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

		// TODO
		return "" + raidName;
	}

	@GetMapping("/all")
	public List<Raid> getAllRaids() {
		return raidService.getAllRaids();
	}

	@GetMapping("{id}")
	public Raid getRaidsById(@PathVariable String id) {
		return raidService.getRaidById(id);
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
	public ResponseEntity<String> newRaid(@RequestBody RaidDTO raidCreateDTO) {
		Raid raid = raidCreateDTO.toRaid();
		raidService.saveRaid(raid);
		System.out.println("String antes de guardar: " + raid.getId());

		return ResponseEntity.status(HttpStatus.CREATED).body("" + raid);
	}

	@PutMapping("{id}")
	public ResponseEntity<String> updateRaid(@PathVariable String id, @RequestBody Raid raid) {
		ResponseEntity<String> verificationResponse = verifyRaidExists(id);
		if (verificationResponse != null) {
			return verificationResponse;
		}
		Raid existingRaid = raidService.getRaidById(id);
		existingRaid.setDifficulty(DifficultyChecker.checkDifficulty(raid.getDifficulty()));
		existingRaid.setClasses(ClassNameChecker.checkClassName(raid.getClasses()));
		existingRaid.setExpansion(ExpansionChecker.checkExpansion(raid.getExpansion()));
		raidService.saveRaid(existingRaid);
		return ResponseEntity.ok("" + raid);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRaid(@PathVariable String id) {
		ResponseEntity<String> verificationResponse = verifyRaidExists(id);
		if (verificationResponse != null) {
			return verificationResponse;
		}
		raidService.deleteRaidById(id);
		String deleteMessage = String.format(FileManager.getText("raid.deleted"), id);
		return ResponseEntity.ok(deleteMessage);
	}

	public ResponseEntity<String> verifyRaidExists(String id) {
		Raid existingRaid = raidService.getRaidById(id);
		if (existingRaid == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(String.format(FileManager.getText("error.raid.notFound"), id));
		}
		return null;
	}

}
