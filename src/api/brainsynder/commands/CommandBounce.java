package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.commands.api.Command;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class CommandBounce extends CommandCore {
	
	public void removeNoFall(UUID uuid) {
		new BukkitRunnable() {

			@Override
			public void run() {
				if(Core.get().getNoFall().contains(uuid)) {
					Core.get().getNoFall().remove(uuid);
				}	
			}
		}.runTaskLater(Core.get(), 240);
	}
	
	@Command(name = "bounce")
	public void run(Player p) {
        if (Core.get().isBlockCommands()) {
            p.sendMessage(prefix + "Sorry, Chat Commands are disabled until further notice.");
            return;
        }
        if(!Cooldown.hasCooldown(p, 15)) {
        	Bukkit.getOnlinePlayers().forEach(Player -> Player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eCatsCraft &6>> " + p.getName() + " &7used their cat ability super jump!")));
        	p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, 20));
        	Core.get().getNoFall().add(p.getUniqueId());
        	removeNoFall(p.getUniqueId());
        	Cooldown.giveCooldown(p);
        }
	}

}
