package com.wow_database.wow.global.handler.exception;

import com.wow_database.wow.global.file.FileManager;

public class RaidNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RaidNotFoundException(String id) {

		super(String.format(FileManager.getText("error.raid.notFound"), id));
	}
}
