/*
 * Copyright (c) created class file on: 2016.
 * All rights reserved.
 * Copyright owner: brainsynder/Magnus498
 * To contact the developer go to:
 * - spigotmc.org and look up brainsynder
 * - email at: briansnyder498@gmail.com
 * - or Skype at live:starwars4393
 */

package EasterEgg.hunt.api.Impl;

import EasterEgg.hunt.api.CustomSkull;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

@Deprecated
public class CustomSkullImpl implements CustomSkull {
	@Override
	public boolean isSkull (ItemStack var0) {
		if ((var0.getType () == Material.SKULL)
						|| (var0.getType () == Material.SKULL_ITEM)) {
			return true;
		}
		return false;
	}

	@Override
	public ItemStack setOwner (ItemStack var0, String var1) {
		if (isSkull (var0)) {
			SkullMeta meta = (SkullMeta)var0.getItemMeta ();
			meta.setOwner (var1);
			var0.setItemMeta (meta);
			return var0;
		}else{
			System.out.println ("[CSkulls] Error: " + var0.getType ().toString () + " is not a skull");
			System.out.println ("[CSkulls] Not setting skull owner.");
		}
		return var0;
	}

	@Override
	public ItemStack setOwner (ItemStack var0, Player var1) {
		if (isSkull (var0)) {
			SkullMeta meta = (SkullMeta)var0.getItemMeta ();
			meta.setOwner (var1.getName ());
			var0.setItemMeta (meta);
			return var0;
		}else{
			System.out.println ("[CSkulls] Error: " + var0.getType ().toString () + " is not a skull");
			System.out.println ("[CSkulls] Not setting skull owner.");
		}
		return var0;
	}

	@Override
	public ItemStack setCustomTexture (String var0, ItemStack var1) {
		Object profile = createProfile (var0);
		if (isSkull (var1)) {
			SkullMeta smeta = (SkullMeta)var1.getItemMeta ();
			applyTextureToMeta (smeta, profile);
			var1.setItemMeta (smeta);
		}
		return var1;
	}

	@Override
	public ItemStack setCustomTexture (String var0) {
		ItemStack var1 = new ItemStack (Material.SKULL_ITEM, 1, (byte)3);
		Object profile = createProfile (var0);
		if (isSkull (var1)) {
			SkullMeta smeta = (SkullMeta)var1.getItemMeta ();
			applyTextureToMeta (smeta, profile);
			var1.setItemMeta (smeta);
		}
		return var1;
	}

	private Object createProfile (String data) {
		try {
			GameProfile profile = new GameProfile (UUID.randomUUID (), "CustomSkulls");
			PropertyMap propertyMap = profile.getProperties ();
			Property property = new Property ("textures", data);
			propertyMap.put ("textures", property);

			return profile;
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
		return null;
	}

	private SkullMeta applyTextureToMeta (SkullMeta meta, Object profile) {
		if (meta == null) {
			throw new IllegalArgumentException ("meta cannot be null");
		}
		if (profile == null) {
			throw new IllegalArgumentException ("profile cannot be null");
		}
		try
		{
			Field profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}
		catch (NoSuchFieldException|IllegalArgumentException|IllegalAccessException e1)
		{
			System.out.println ("[CSkulls] Error: A problem o when setting the custom tecture");
		}
		return meta;
	}
}
