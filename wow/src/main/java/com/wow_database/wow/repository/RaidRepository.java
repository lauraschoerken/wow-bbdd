package com.wow_database.wow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wow_database.wow.model.entity.Raid;
import com.wow_database.wow.model.enums.Expansion;

public interface RaidRepository extends JpaRepository<Raid, Long> {

	@Query("SELECT r FROM Raid r WHERE r.difficulty LIKE %:difficulty%")
	List<Raid> findByDifficulty(@Param("difficulty") String difficulty);

	@Query("SELECT r FROM Raid r WHERE r.classes LIKE %:className%")
	List<Raid> findByClasses(@Param("className") String className);

	@Query("SELECT r FROM Raid r WHERE r.expansion = :expansion")
	List<Raid> findByExpansion(@Param("expansion") Expansion expansion);

}
