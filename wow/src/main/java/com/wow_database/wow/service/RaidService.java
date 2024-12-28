package com.wow_database.wow.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wow_database.wow.global.file.FileManager;
import com.wow_database.wow.global.handler.exception.RaidNotFoundException;
import com.wow_database.wow.model.dto.RaidCreateDTO;
import com.wow_database.wow.model.dto.RaidPartialDTO;
import com.wow_database.wow.model.entity.Raid;
import com.wow_database.wow.model.entity.User;
import com.wow_database.wow.model.enums.Difficulty;
import com.wow_database.wow.model.enums.Expansion;
import com.wow_database.wow.model.enums.checker.DifficultyChecker;
import com.wow_database.wow.model.enums.checker.ExpansionChecker;
import com.wow_database.wow.repository.RaidRepository;
import com.wow_database.wow.repository.UserRepository;

import jakarta.persistence.Tuple;

@Service
public class RaidService {

	@Autowired
	private RaidRepository raidRepository;
	@Autowired
	private UserRepository userRepository;

	public Raid saveRaid(Raid raid) {
		return raidRepository.save(raid);
	}

	public List<Raid> getAllRaids() {
		return raidRepository.findAll();
	}

	public List<Raid> getAllRaidsByUser(Long userId) {
		return raidRepository.findByUserId(userId);
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

	public List<Raid> getRaidsByExpansion(String expansionStr) {
		try {
			Expansion.valueOf(expansionStr.toUpperCase());
			return raidRepository.findByExpansion(expansionStr);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(String.format(FileManager.getText("invalid.expansion"), expansionStr));
		}
	}

	public Raid createRaidFromDTO(RaidCreateDTO raidDTO) {
		User user = userRepository.findById(raidDTO.getUserId()).orElseThrow(
				() -> new IllegalArgumentException("Usuario no encontrado con ID: " + raidDTO.getUserId()));

		Raid raid = new Raid(raidDTO.getName(), ExpansionChecker.checkExpansion(raidDTO.getExpansion()),
				raidDTO.getMounts(), raidDTO.getTransmogs(), raidDTO.getAchievements(),
				DifficultyChecker.checkDifficulty(raidDTO.getDifficulty()), user);

		return raidRepository.save(raid);
	}

	public List<Raid> getAllRaidsByUserFull(Long userId) {
		return raidRepository.findByUserId(userId);

		// return raidRepository.findByUserIdFull(userId);
	}

	public List<RaidPartialDTO> getAllRaidsByUserPartial(Long userId) {
		List<Tuple> tuples = raidRepository.findPartialByUserId(userId);
		List<RaidPartialDTO> raids = new ArrayList<>();

		for (Tuple tuple : tuples) {
			String id = tuple.get(0, String.class); // Accede a los valores del Tuple
			String name = tuple.get(1, String.class);
			String expansion = tuple.get(2, String.class);

			raids.add(new RaidPartialDTO(id, name, expansion));
		}

		return raids;
	}

}
