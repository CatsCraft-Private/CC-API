/*
 * Copyright (c) created class file on: 2016.
 * All rights reserved.
 * Copyright owner: brainsynder/Magnus498
 * To contact the developer go to:
 * - spigotmc.org and look up brainsynder
 * - email at: briansnyder498@gmail.com
 * - or Skype at live:starwars4393
 */

package EasterEgg.hunt;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

@Deprecated
public class MainEggHunt {
	public static Map<Skull, EasterEgg> eggs;
	public static Map<String, PlayerFinder> playerFinder;
	public static int EggCount;

	public static void savePlayer (Player p) {
		if (playerFinder.containsKey (p.getName ())) {
			PlayerFinder finder = playerFinder.get (p.getName ());
			finder.save ();
		}
	}

	public static void load (JavaPlugin plugin) {
		eggs = new HashMap<> ();
		playerFinder = new HashMap<> ();
		EggCount = 0;
		plugin.getCommand ("EasterEgg").setExecutor (new EggCommand ());
		plugin.getServer ().getPluginManager ().registerEvents (new EggListener (), plugin);
		if (EggFile.getSection ("Eggs") != null) {
			for (String s : EggFile.getSection ("Eggs").getKeys (false)) {
				String main = "Eggs." + s + '.';
				int id = Integer.parseInt (s);
				String hint = EggFile.getString (main + "Hint");
				double x = EggFile.getDouble (main + "Location.X");
				double y = EggFile.getDouble (main + "Location.Y");
				double z = EggFile.getDouble (main + "Location.Z");
				World world = Bukkit.getWorld (EggFile.getString (main + "Location.World"));
				Location loc = new Location (world, x, y, z);
				if (loc.getBlock ().getType ().equals (Material.SKULL)) {
					EggCount++;
					Skull skull = (Skull) loc.getBlock ().getState ();
					eggs.put (skull, new EasterEgg (skull, id, hint));
				}
			}
		}
		if (Bukkit.getOnlinePlayers ().size () != 0) {
			for (Player players : Bukkit.getOnlinePlayers ()) {
				if (!playerFinder.containsKey (players.getName ())) {
					playerFinder.put (players.getName (), new PlayerFinder (players));
				}
			}
		}
	}
}
