package me.quartzdev.DeathCrystals.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public class Converters {
	
	public static String toBase64(Inventory inventory) {
		try {
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
			
			// Write the size of the inventory
			dataOutput.writeInt(inventory.getSize());
			
			// Save every element in the list
			for (int i = 0; i < inventory.getSize(); i++) {
				dataOutput.writeObject(inventory.getItem(i));
			}
			
			// Serialize that array
			dataOutput.close();
			return Base64Coder.encodeLines(outputStream.toByteArray()).replaceAll("\r\n", "&&").replaceAll("==", "=/=");
		} catch (Exception e) {
			throw new IllegalStateException("Unable to save item stacks.", e);
		}
	}
	
	public static Inventory fromBase64(String data) throws IOException {
		try {
			ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data.replaceAll("&&", "\r\n").replaceAll("=/=", "==")));
			BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
			Inventory inventory = Bukkit.getServer().createInventory(null, dataInput.readInt());
			
			// Read the serialized inventory
			for (int i = 0; i < inventory.getSize(); i++) {
				inventory.setItem(i, (ItemStack) dataInput.readObject());
			}
			dataInput.close();
			return inventory;
		} catch (ClassNotFoundException e) {
			throw new IOException("Unable to decode class type.", e);
		}
	}
	
	public static long convertLongTime(String time) {
		long finalTime = 0;
		
		String formatTime = "0";
		for (char c : time.toCharArray()) {
			if (Character.isDigit(c)) {
				formatTime += c;
			} else if (Character.isAlphabetic(c)) {
				for (TimeType tt : TimeType.values()) {
					if (c == tt.getTimeChar()) {
						finalTime += Long.valueOf(formatTime) * tt.getAmountMillis();
						formatTime = "0";
						break;
					}
				}
			}
		}
		
		return finalTime;
	}
	
	public static String convertStringTime(long time) {
		String finalTime = "";
		
		long workingTime = 0;
		
		for (TimeType tt : TimeType.values()) {
			while (time >= tt.getAmountMillis()) {
				time -= tt.getAmountMillis();
				workingTime++;
			}
			if (workingTime > 0) {
				finalTime += workingTime + tt.getTimeChar();
			}
			workingTime = 0;
		}
		
		return finalTime;
	}
	
	public enum TimeType {
		
		SECONDS("Seconds", 's', 1000), MINUTES("Minutes", 'm', 60000), HOURS("Hours", 'h', 3600000), DAYS("Days", 'd', 86400000), WEEKS("Weeks", 'w', 604800000), YEARS("Years", 'y', 31536000000l), CENTURIES("Centuries", 'c', 3153600000000l);
		
		private String timeName;
		private char timeChar;
		private long amountMillis;
		
		private TimeType(String timeName, char timeChar, long amountMillis) {
			this.timeName = timeName;
			this.timeChar = timeChar;
			this.amountMillis = amountMillis;
		}
		
		protected String getTimeName() {
			return timeName;
		}
		
		protected char getTimeChar() {
			return timeChar;
		}
		
		protected long getAmountMillis() {
			return amountMillis;
		}
		
	}
	
}
