package com.wow_database.wow.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
public class Raid {

	private static final Logger logger = LoggerFactory.getLogger(Raid.class);

	@Id
	@Column(columnDefinition = "VARCHAR(36)", updatable = false, nullable = false)
	@Setter(AccessLevel.NONE)
	private String id;

	private String name;
	private String expansion;
	private String mounts;
	private String transmogs;
	private String achievements;
	private String difficulty;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	private LocalDateTime created;
	private LocalDateTime lastUpdate;
	// TO DO character list

	@PrePersist
	public void generarUUID() {
		if (id == null) {
			id = UUID.randomUUID().toString();
		}
	}

	public Raid() {
		this.id = UUID.randomUUID().toString();
	}

	public Raid(String name, String expansion, String mounts, String transmogs, String achievements, String difficulty,
			User user) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.expansion = expansion;
		this.mounts = mounts;
		this.transmogs = transmogs;
		this.achievements = achievements;
		this.difficulty = difficulty;
		this.created = LocalDateTime.now();
		this.lastUpdate = LocalDateTime.now();
		this.user = user;
		logger.info("UUID antes de guardar: {}", this.id);

	}
}
