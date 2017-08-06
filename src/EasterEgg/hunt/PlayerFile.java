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

import api.brainsynder.Core;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Deprecated
public class PlayerFile {
	public static String getString (Player p, String tag) {
		File f = new File (Core.get ().getDataFolder () + "/PlayerData", p.getUniqueId ().toString () + ".yml");
		FileConfiguration con = YamlConfiguration.loadConfiguration (f);
		return translate (con.getString (tag));
	}

	public static int getInt (Player p, String tag) {
		File f = new File (Core.get ().getDataFolder () + "/PlayerData", p.getUniqueId ().toString () + ".yml");
		if (!f.exists ()) {
			try {
				f.createNewFile ();
			}
			catch (IOException e) {
				e.printStackTrace ();
			}
		}
		FileConfiguration con = YamlConfiguration.loadConfiguration (f);
		return con.getInt (tag);
	}

	public static List<String> getList (Player p, String tag) {
		File f = new File (Core.get ().getDataFolder () + "/PlayerData", p.getUniqueId ().toString () + ".yml");
		if (!f.exists ()) {
			try {
				f.createNewFile ();
			}
			catch (IOException e) {
				e.printStackTrace ();
			}
		}
		FileConfiguration con = YamlConfiguration.loadConfiguration (f);
		return con.getStringList (tag);
	}

	public static double getDouble (Player p, String tag) {
		File f = new File (Core.get ().getDataFolder () + "/PlayerData", p.getUniqueId ().toString () + ".yml");
		FileConfiguration con = YamlConfiguration.loadConfiguration (f);
		return con.getDouble (tag);
	}

	public static ConfigurationSection getSection (Player p, String tag) {
		File f = new File (Core.get ().getDataFolder () + "/PlayerData", p.getUniqueId ().toString () + ".yml");
		FileConfiguration con = YamlConfiguration.loadConfiguration (f);
		return con.getConfigurationSection (tag);
	}

	public static void set (Player p, String tag, Object data) {
		File f = new File (Core.get ().getDataFolder () + "/PlayerData", p.getUniqueId ().toString () + ".yml");
		if (!f.exists ()) {
			try {
				f.createNewFile ();
			}
			catch (IOException e) {
				e.printStackTrace ();
			}
		}
		FileConfiguration con = YamlConfiguration.loadConfiguration (f);
		con.set (tag, data);
		try {
			con.save (f);
		}
		catch (IOException e) {
			e.printStackTrace ();
		}
	}

	public static String translate (String msg) {
		return ChatColor.translateAlternateColorCodes ('&', msg);
	}

}
