package api.brainsynder.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Ocelot.Type;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.commands.api.Command;

public class CommandStealCats extends CommandCore {
	
	private Type randomCatType() {
		List<Type> types = new ArrayList<>();
		types.add(Type.BLACK_CAT);
		types.add(Type.RED_CAT);
		types.add(Type.SIAMESE_CAT);
		Type toReturn = types.get(new Random().nextInt(types.size()));
		return toReturn;
	}
	
	public void removeEntity(Entity e) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Core.get(), new Runnable() {
			@Override
			public void run() {
				if (e != null) {
					e.remove();
				}
			}
		}, 40);
	}
	
	private void spawnCats(Player p) {
		for (int x1 = 0; x1 <= 6; x1++) {
			double x = -0.5F + (float) (Math.random() * 0.9D);
			double y = 0.5D;
			double z = -0.5F + (float) (Math.random() * 0.9D);
			Ocelot ocelot = (Ocelot) p.getWorld().spawnEntity(p.getLocation(), EntityType.OCELOT);
			ocelot.setCustomName(ChatColor.translateAlternateColorCodes('&',"&6Stolen Cat"));
			ocelot.setVelocity(new Vector(x, y, z));
			ocelot.setCatType(randomCatType());
			removeEntity(ocelot);
		}
	}

	@Command(name = "stealcats")
	public void run(Player p) {
        if (Core.get().isBlockCommands()) {
            p.sendMessage(prefix + "Sorry, Chat Commands are disabled until further notice.");
            return;
        }
        if(!Cooldown.hasCooldown(p)) {
			Bukkit.getOnlinePlayers().forEach(Player -> Player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eCatsCraft &6>> " + p.getName() + " &7tried to steal all the cats but their pockets exploded!")));
			spawnCats(p);
			Cooldown.giveCooldown(p);
        }
	}
}
