package com.wow_database.wow.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RaidSummaryDTO {

	private String id;
	private String name;
	private String expansion;
	private String mounts;
	private String transmogs;
	private String achievements;
	private String difficulty;
	private Long user;
	private LocalDateTime created;
	private LocalDateTime lastUpdate;
	// TO DO character list

}
