package com.wow_database.wow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<Raid> getRaidsByDifficulty(String difficultyStr) {
		try {
			Difficulty.valueOf(difficultyStr.toUpperCase());
			return raidRepository.findByDifficulty(difficultyStr);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Dificultad no válida: " + difficultyStr);
		}
	}

	public List<Raid> getRaidsByClass(String classStr) {
		try {
			ClassName.valueOf(classStr.toUpperCase());
			return raidRepository.findByClasses(classStr);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Clase no válida: " + classStr);
		}
	}

	public List<Raid> getRaidsByExpansion(String expansionStr) {
		try {
			Expansion expansion = Expansion.valueOf(expansionStr.toUpperCase());
			return raidRepository.findByExpansion(expansion);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Expansion no válida: " + expansionStr);
		}
	}

}
