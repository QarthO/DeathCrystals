package me.quartzdev.DeathCrystals.utils;

import me.quartzdev.DeathCrystals.Language;

public class ExpiredCrystalException extends Exception {
	
	private static final long serialVersionUID = -8379340561580334055L;
	
	int id;
	long expirationDate;
	
	public ExpiredCrystalException(int id, long expirationDate) {
		this.id = id;
		this.expirationDate = expirationDate;
	}
	
	public String getMessage() {
		return String.format(Language.CRYSTAL_EXPIRED.getMessage(), id, expirationDate);
	}
	
}
