package com.wow_database.wow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wow_database.wow.global.file.FileManager;
import com.wow_database.wow.global.handler.exception.RaidNotFoundException;
import com.wow_database.wow.model.entity.Raid;
import com.wow_database.wow.model.enums.ClassName;
import com.wow_database.wow.model.enums.Difficulty;
import com.wow_database.wow.model.enums.Expansion;
import com.wow_database.wow.repository.RaidRepository;

@Service
public class RaidService {

	@Autowired
	private RaidRepository raidRepository;

	public Raid saveRaid(Raid raid) {
		return raidRepository.save(raid);
	}

	public List<Raid> getAllRaids() {
		return raidRepository.findAll();
	}

	public Raid getRaidById(String id) {
		Optional<Raid> raidOptional = raidRepository.findById(id);
		return raidOptional.orElseThrow(() -> new RaidNotFoundException(id));
	}

	public void deleteRaidById(String id) {
		raidRepository.deleteById(id);
	}

	public List<Raid> getRaidsByDifficulty(String difficultyStr) {
		try {
			Difficulty.valueOf(difficultyStr.toUpperCase());
			return raidRepository.findByDifficulty(difficultyStr);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(String.format(FileManager.getText("invalid.difficulty"), difficultyStr));
		}
	}

	public List<Raid> getRaidsByClass(String classStr) {
		try {
			ClassName.valueOf(classStr.toUpperCase());
			return raidRepository.findByClasses(classStr);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(String.format(FileManager.getText("invalid.class"), classStr));
		}
	}

	public List<Raid> getRaidsByExpansion(String expansionStr) {
		try {
			Expansion.valueOf(expansionStr.toUpperCase());
			return raidRepository.findByExpansion(expansionStr);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(String.format(FileManager.getText("invalid.expansion"), expansionStr));
		}
	}

}
