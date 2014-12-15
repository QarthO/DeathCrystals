package me.quartzdev.DeathCrystals;

import org.bukkit.ChatColor;

public enum Language {
	
	BORDER_BOTTOM(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "-----------------" + ChatColor.LIGHT_PURPLE + " Created By QarthO " + ChatColor.BLUE + " " + ChatColor.STRIKETHROUGH + " ----------------"),
	BORDER_TOP(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "------------------" + ChatColor.LIGHT_PURPLE + " Project v%s" + ChatColor.BLUE + " " + ChatColor.STRIKETHROUGH + " ------------------"),
	CHAT_PREFIX(ChatColor.DARK_GREEN + "[" + ChatColor.BLUE + "Project" + ChatColor.DARK_GREEN + "] " + ChatColor.RESET),
	CONSOLE_MESSAGE(ChatColor.RED + "Error: In game command only"),
	HELP("you need help"),
	HELP_ADMIN("admin help"),
	HELP_MOD("mod help"),
	INCORRECT_USAGE(ChatColor.RED + "Error: Incorrect usage"),
	NO_PERMISSIONS(ChatColor.RED + "Error: Insuffictient permissions"),
	PLAYER_NOT_FOUND(ChatColor.RED + "Error: Player not found"),
	UKNOWN_COMMAND(ChatColor.RED + "Not a valid command\nTry /dc help"),
	CRYSTAL_LORE(ChatColor.GREEN + "Right-Click to Open Inventory"),
	CRYSTAL_NOT_FOUND(ChatColor.RED + "The crystal with ID %s could not be found."),
	CRYSTAL_EXPIRED(ChatColor.RED + "The crystal with ID %s expired on %s");
	
	String Message;
	
	private Language(String message) {
		this.Message = message;
	}
	
	public String getMessage() {
		return Message;
	}
	
	public static Language[] getMessages() {
		Language[] e = { HELP, CHAT_PREFIX };
		return e;
	}
	
}
