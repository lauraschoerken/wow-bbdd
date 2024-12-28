package com.wow_database.wow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wow_database.wow.model.entity.Raid;

import jakarta.persistence.Tuple;

public interface RaidRepository extends JpaRepository<Raid, String> {

	@Query("SELECT r FROM Raid r WHERE r.difficulty LIKE %:difficulty%")
	List<Raid> findByDifficulty(@Param("difficulty") String difficulty);

	@Query("SELECT r FROM Raid r WHERE r.expansion = :expansion")
	List<Raid> findByExpansion(@Param("expansion") String expansion);

	@Query("SELECT r.id, r.name, r.expansion FROM Raid r WHERE r.user.id = :userId")
	List<Tuple> findPartialByUserId(@Param("userId") Long userId);

	List<Raid> findByUserId(Long userId);

}
