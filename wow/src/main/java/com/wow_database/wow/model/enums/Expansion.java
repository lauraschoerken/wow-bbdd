package com.wow_database.wow.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Expansion {
	CLASSIC, THE_BURNING_CRUSADE, LICH_KING, CATACLYSM, MISTS_OF_PANDARIA, WARLORDS_OF_DRAENOR, LEGION, BFA,
	SHADOWLANDS, DRAGONFLIGHT;

	public static boolean isValidExpansion(String value) {
		try {
			Expansion.valueOf(value.toUpperCase());
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	public static Expansion transformExpansion(String value) {
		try {
			return Expansion.valueOf(value.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid expansion value: " + value);
		}
	}

	@JsonCreator
	public static Expansion fromString(String key) {
		return key == null ? null : Expansion.valueOf(key.trim().toUpperCase());
	}

	@JsonValue
	public String toValue() {
		return this.name();
	}
}
