package com.wow_database.wow.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

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
	@JsonProperty("user")
	private Long userId;

}
