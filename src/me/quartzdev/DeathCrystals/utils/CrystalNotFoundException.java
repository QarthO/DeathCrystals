package me.quartzdev.DeathCrystals.utils;

import me.quartzdev.DeathCrystals.Language;

public class CrystalNotFoundException extends Exception {
	
	private static final long serialVersionUID = 5922680342131547902L;
	int id;
	
	public CrystalNotFoundException(int id) {
		this.id = id;
	}
	
	public String getMessage() {
		return String.format(Language.CRYSTAL_NOT_FOUND.getMessage(), id);
	}
	
}
