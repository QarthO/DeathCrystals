package me.quartzdev.DeathCrystals;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Commands extends JavaPlugin{
	Plugin plugin;
	String version = plugin.getDescription().getVersion();
	String Project_Name = "DeathCrystals";
	String CMD_Label = "deathcrystals";
	String CMD_Alias = "dc";
	
	private boolean isCommand(String cmd){
		String[] CMDs= {"help", "enable", "disable", "reload", "debug"};
		for(String command : CMDs){
			if(cmd.equalsIgnoreCase(command)){
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage(Language.CONSOLE_MESSAGE.getMessage());
			return super.onCommand(sender, command, commandLabel, args);
		}
		Player player = (Player) sender;
		if(commandLabel.equals(CMD_Label) || commandLabel.equals(CMD_Alias)){
			if(args.length == 0){
				player.sendMessage(ChatColor.LIGHT_PURPLE + Project_Name + " v" + version);
				return false;
			}
			if(isCommand(commandLabel)){
				
			} else{
				player.sendMessage(Language.UKNOWN_COMMAND.getMessage());
			}
			
		}
		return super.onCommand(sender, command, commandLabel, args);
	}
}
