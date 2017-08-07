package api.brainsynder.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.commands.api.Command;

public class CommandPancake extends CommandCore {

	@Command(name = "pancake")
	public void run(Player p) {
		if (Core.get().isBlockCommands()) {
			p.sendMessage(prefix + "Sorry, Chat Commands are disabled until further notice.");
			return;
		}
		if (!Cooldown.hasCooldown(p)) {
			Bukkit.getOnlinePlayers().forEach(Player -> Player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&eCatsCraft &6>> " + p.getName() + " &eis eating pancakes &dYummmmm!")));
			Cooldown.giveCooldown(p);
		}
	}

}
