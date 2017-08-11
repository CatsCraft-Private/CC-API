package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.commands.api.Command;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommandSonic extends CommandCore {
	
	@Command(name = "sonic")
	public void run(Player p) {
        if (Core.get().isBlockCommands()) {
            p.sendMessage(prefix + "Sorry, Chat Commands are disabled until further notice.");
            return;
        }
        if(!Cooldown.hasCooldown(p, 15)) {
        	Bukkit.getOnlinePlayers().forEach(Player -> Player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eCatsCraft &6>> " + p.getName() + " &7turned into Sonic &eGotta go fast!")));
        	p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 50));
        	Cooldown.giveCooldown(p);
        }
	}

}
