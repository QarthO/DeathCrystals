package me.quartzdev.DeathCrystals;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Commands extends JavaPlugin{
	Plugin plugin;
	@Override
	public void onEnable() {
		super.onEnable();
	}
	@Override
	public void onDisable() {
		super.onDisable();
	}
	
	String version = plugin.getDescription().getVersion();
	String Project_Name = "DeathCrystals";
	String CMD_Label = "deathcrystals";
	String CMD_Alias = "dc";
	
	@Override
	public boolean onCommand(CommandSender sender, Command commandLabel, String label, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage(Language.CONSOLE_MESSAGE.getMessage());
			return super.onCommand(sender, commandLabel, label, args);
		}
		Player player = (Player) sender;
		if(commandLabel.equals(CMD_Label) || commandLabel.equals(CMD_Alias)){
			if(args.length == 0){
				player.sendMessage(ChatColor.LIGHT_PURPLE + Project_Name + " v" + version);
			}
		}
		return super.onCommand(sender, commandLabel, label, args);
	}
}
