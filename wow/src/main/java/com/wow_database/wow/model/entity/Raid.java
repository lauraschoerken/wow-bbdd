package com.wow_database.wow.model.entity;

import com.wow_database.wow.model.enums.RaidClass;
import com.wow_database.wow.model.enums.RaidDifficulty;
import com.wow_database.wow.model.enums.RaidExpansion;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Raid {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Enumerated(EnumType.STRING)
	private RaidExpansion expansion;

	private String mounts;

	@Enumerated(EnumType.STRING)
	private RaidClass classes;

	private String transmogs;
	private String achivements;

	@Enumerated(EnumType.STRING)
	private RaidDifficulty difficulties;

}
