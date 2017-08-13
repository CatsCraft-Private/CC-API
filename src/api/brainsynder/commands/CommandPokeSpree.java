package api.brainsynder.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.commands.api.Command;

public class CommandPokeSpree extends CommandCore {
	
	
	@Command(name = "pokespree")
	public void run(Player p) {
        if (Core.get().isBlockCommands()) {
            p.sendMessage(prefix + "Sorry, Chat Commands are disabled until further notice.");
            return;
        }
        
        if(!Cooldown.hasCooldown(p)) {
        	Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&5[&d&lTP&5] " + p.getName() + " &7Pokes &5Everyone!! &7*Boop!* *Boop!*")));
        	Cooldown.giveCooldown(p);
        }
	}
}
