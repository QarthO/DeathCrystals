package me.quartzdev.DeathCrystals;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor{
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
			return false;
		}
		Player player = (Player) sender;
		if(commandLabel.equals(CMD_Label) || commandLabel.equals(CMD_Alias)){
			if(args.length == 0){
				player.sendMessage(ChatColor.LIGHT_PURPLE + Project_Name + " v" + "0.0.4");
				return false;
			}
			if(isCommand(args[0])){
				if(args[0].equalsIgnoreCase("help")){
					if(args.length == 1){
						player.sendMessage(Language.BORDER_TOP.getMessage());
						player.sendMessage(Language.HELP.getMessage());
						player.sendMessage(Language.BORDER_BOTTOM.getMessage());
					} else{
						player.sendMessage(Language.INCORRECT_USAGE.getMessage());
					}
				}
			} else{
				player.sendMessage(Language.UKNOWN_COMMAND.getMessage());
			}
			
		}
		return false;
	}
}
