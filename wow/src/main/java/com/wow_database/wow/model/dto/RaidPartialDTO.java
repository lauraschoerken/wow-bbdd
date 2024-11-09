package com.wow_database.wow.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaidPartialDTO {
	private String id;
	private String name;
	private String expansion;
}
