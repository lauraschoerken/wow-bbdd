package com.wow_database.wow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wow_database.wow.model.entity.Raid;
import com.wow_database.wow.repository.RaidRepository;

@Service
public class RaidService {

	@Autowired
	private RaidRepository raidRepository;

	public Raid saveRaid(Raid raid) {
		return raidRepository.save(raid);
	}

	public List<Raid> getAllRaids() {
		return raidRepository.findAll();
	}

}
