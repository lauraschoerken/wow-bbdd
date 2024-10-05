package com.wow_database.wow.model.dto;

import com.wow_database.wow.model.entity.Raid;
import com.wow_database.wow.model.enums.checker.ClassNameChecker;
import com.wow_database.wow.model.enums.checker.DifficultyChecker;
import com.wow_database.wow.model.enums.checker.ExpansionChecker;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RaidDTO {
	private String name;
	private String expansion;
	private String mounts;
	private String classes;
	private String transmogs;
	private String achievements;
	private String difficulty;

	public Raid toRaid() {
		return new Raid(name, ExpansionChecker.checkExpansion(expansion), mounts,
				ClassNameChecker.checkClassName(classes), transmogs, achievements,
				DifficultyChecker.checkDifficulty(difficulty));
	}
}
