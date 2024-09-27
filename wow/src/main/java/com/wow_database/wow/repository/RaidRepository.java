package com.wow_database.wow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wow_database.wow.model.entity.Raid;

public interface RaidRepository extends JpaRepository<Raid, Long> {

}
