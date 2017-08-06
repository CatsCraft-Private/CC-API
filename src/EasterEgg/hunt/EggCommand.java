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

import EasterEgg.hunt.api.CSkullPlugin;
import EasterEgg.hunt.api.CustomSkull;
import api.brainsynder.Utils.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@Deprecated
public class EggCommand implements CommandExecutor {
	@Override
	public boolean onCommand (CommandSender commandSender, Command command, String s, String[] strings) {
		if (command.getName ().equalsIgnoreCase ("EasterEgg")) {
			if (commandSender instanceof Player) {
				Player p = (Player) commandSender;
				if (isAllowed (p.getName ())) {
					open (p);
				}
			}
		}
		return false;
	}

	private boolean isAllowed (String name) {
		if (name.equals ("Tannianna")) {
			return true;
		}
		if (name.equals ("migigurl")) {
			return true;
		}
		if (name.equals ("angrylawnnGnome")) {
			return true;
		}
		if (name.equals ("brainsynder")) {
			return true;
		}
		return false;
	}

	private void open (Player p) {
		Inventory inv = Bukkit.createInventory (null, 18, "EasterEggs");
		inv.addItem (getItem (1, "Yzc2NTk1ZWZmY2M1NjI3ZTg1YjE0YzljODgyNDY3MWI1ZWMyOTY1NjU5YzhjNDE3ODQ5YTY2Nzg3OGZhNDkwIn19fQ=="));
		inv.addItem (getItem (2, "MjY0NDMwZTQ5M2ZlYjVlYWExNDU1ODJlNTRlNzYxYTg2MDNmYjE2Y2MwZmYxMjY4YTVkMWU4NjRlNmY0NzlmNiJ9fX0="));
		inv.addItem (getItem (3, "YjNkNjliMjNhZTU5MmM2NDdlYjhkY2ViOWRhYWNlNDQxMzlmNzQ4ZTczNGRjODQ5NjI2MTNjMzY2YTA4YiJ9fX0="));
		inv.addItem (getItem (4, "MWYxOGI5YTQzNmEyN2Y4MTNjMjg3ZWI2NzM3OWVmOGFkYmZkYzcwYWZhZjMwNGM0M2IxNjZjZTk4NmRkOCJ9fX0="));
		inv.addItem (getItem (5, "YjVkYmVjNTI0YTk1ZGVhNGQ0ZDU4MDJjOTViMGNmNWVhZmViZDMxYTk4NzY3NzI3MTZhNDg2MmQ2Yzg4YSJ9fX0="));
		inv.addItem (getItem (6, "ZWViMzM1MTgyZGI1ZjNiZTgwZmNjZjZlYWJlNTk5ZjQxMDdkNGZmMGU5ZjQ0ZjM0MTc0Y2VmYTZlMmI1NzY4In19fQ=="));
		inv.addItem (getItem (7, "OTg4OWYxMWM4ODM4YzA5ZTFlY2YyZjgzNDM5ZWJjYjlmMzI0ZTU2N2IwZTlkYzRiN2MyNWQ5M2U1MGZmMmIifX19"));
		inv.addItem (getItem (8, "ZDM2ZmU4M2M0MmM2Y2U3Mzg2Y2YyMzMzYTFjNTk1ZDBiNDMzZGE3YmM1NTkyYjg2ODY2ODU1MWQ2OWI5YjAifX19"));
		inv.addItem (getItem (9, "MTViOGRjYmVhMjdmNDJmNWFlOTEwNDQ1ZTA1ZGFjODlkMzEwYWFmMjM2YTZjMjEyM2I4NTI4MTIwIn19fQ=="));
		inv.addItem (getItem (10, "MTNlOGJiYzhkMTc0YWVjZDZiNDY4ODhmYTYzZjliYWRlMTRiMDQyZTVlMTcwNjMxMzlkNjdmOGUwMTYzYTM4In19fQ=="));
		p.openInventory (inv);
	}

	private ItemStack getItem (int id, String url) {
		CustomSkull skulls = CSkullPlugin.getSkullMaker ();
		return skulls.setCustomTexture ("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUv"+url, ItemFactory.create (Material.SKULL_ITEM, (byte) 3, "Easter Egg: " + id));
	}
}
