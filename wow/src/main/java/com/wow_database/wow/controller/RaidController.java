package com.wow_database.wow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class RaidController {

	@GetMapping("raids")
	public String getMethodName(@RequestParam String param) {
		return new String();
	}

	public String hi() {
		return "hi";
	}
}
