/*
 * Copyright (c) created class file on: 2016.
 * All rights reserved.
 * Copyright owner: brainsynder/Magnus498
 * To contact the developer go to:
 * - spigotmc.org and look up brainsynder
 * - email at: briansnyder498@gmail.com
 * - or Skype at live:starwars4393
 */

package EasterEgg.hunt.api;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Deprecated
public interface CustomSkull {
	boolean isSkull (ItemStack var0);

	ItemStack setOwner (ItemStack var0, String var1);

	ItemStack setOwner (ItemStack var0, Player var1);

	ItemStack setCustomTexture (String var0, ItemStack var1);

	ItemStack setCustomTexture (String var0);
}
