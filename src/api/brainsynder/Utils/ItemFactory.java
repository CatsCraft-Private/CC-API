/*
 * Copyright (c) created class file on: 2016.
 * All rights reserved.
 * Copyright owner: brainsynder/Magnus498
 * To contact the developer go to:
 * - spigotmc.org and look up brainsynder
 * - email at: briansnyder498@gmail.com
 * - or Skype at live:starwars4393
 */

package api.brainsynder.Utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Deprecated
public class ItemFactory {

	public static List<String> colorizeLore (List<String> list) {
		List<String> toReturn = new ArrayList<> ();
		for (String s : list) {
			toReturn.add (ChatColor.translateAlternateColorCodes ('&', s));
		}
		return toReturn;
	}

	public static GameProfile getNonPlayerProfile(String skinURL) {
		GameProfile newSkinProfile = new GameProfile(UUID.randomUUID(), null);
		newSkinProfile.getProperties().put("textures", new Property("textures", Base64Coder.encodeString("{textures:{SKIN:{url:\"" + skinURL + "\"}}}")));
		return newSkinProfile;
	}


	public static List<String> colorizeLore (String... list) {
		List<String> toReturn = new ArrayList<> ();
		for (String s : list) {
			toReturn.add (ChatColor.translateAlternateColorCodes ('&', s));
		}
		return toReturn;
	}

	public static ItemStack create(Material material, byte data, String displayName, List<String> lore)
	{
		ItemStack itemStack = new MaterialData(material, data).toItemStack(1);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(displayName.replace ("&", "§"));
		if (lore != null)
		{
			itemMeta.setLore(colorizeLore (lore));
		}
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}





	public static ItemStack createSkull(String name, String urlToFormat)
	{
		String url = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUv" + urlToFormat;
		ItemStack head = create(Material.SKULL_ITEM, (byte)3, name.replace ("&", "§"));
		if (url.isEmpty()) {
			return head;
		}
		SkullMeta headMeta = (SkullMeta)head.getItemMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		profile.getProperties().put ("textures", new Property ("textures", url));
		try
		{
			Field profileField = headMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(headMeta, profile);
		}
		catch (NoSuchFieldException|IllegalArgumentException|IllegalAccessException e1)
		{
			e1.printStackTrace();
		}
		head.setItemMeta (headMeta);
		return head;
	}

	public static ItemStack create(Material material, byte data, String displayName, List<String> finalLore, boolean show)
	{
		ItemStack itemStack = new MaterialData(material, data).toItemStack (1);
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setDisplayName(displayName);
		itemMeta.setLore (finalLore);
		itemStack.setItemMeta (itemMeta);
		return itemStack;
	}

	public static ItemStack create(Material material, byte data, String displayName)
	{
		return create(material, data, displayName, null);
	}

	public static ItemStack createSkull( Player p)
	{
		ItemStack head = create(Material.SKULL_ITEM, (byte)3, "AMORSTAND MOUNT HEAD");
		SkullMeta headMeta = (SkullMeta)head.getItemMeta();
		headMeta.setOwner (p.getName());
		head.setItemMeta(headMeta);
		return head;
	}

	public static GameProfile createGameProfile(String url)
	{
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		PropertyMap propertyMap = profile.getProperties();
		if (propertyMap == null)
		{
			return null;
		}

		//byte[] encodedData = new byte[] {String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes ()};
		propertyMap.put ("textures", new Property ("textures", String.format ("{textures:{SKIN:{url:\"%s\"}}}", url)));

		return profile;
	}

	public static ItemStack createItemStack (String fileName, ChatColor color) {
		ItemStack head = create(Material.SKULL_ITEM, (byte)3, color + fileName.replace ("&", "§"));
		if (fileName.isEmpty()) {
			return head;
		}
		SkullMeta headMeta = (SkullMeta)head.getItemMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		profile.getProperties().put("textures", new Property("textures", "http://brainsynder.tk/textures/" + fileName + ".png"));
		try
		{
			Field profileField = headMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(headMeta, profile);
		}
		catch (NoSuchFieldException|IllegalArgumentException|IllegalAccessException e1)
		{
			e1.printStackTrace();
		}
		head.setItemMeta(headMeta);
		return head;
	}


	public static class skullMaker {

		public static String getBukkitVersion()
		{
			Matcher matcher = Pattern.compile ("v\\d+_\\d+_R\\d+").matcher(Bukkit.getServer ().getClass().getPackage().getName());
			if (matcher.find()) {
				return matcher.group();
			}
			return null;
		}

		public static ItemStack convertUrl (String fileName){
			return convert (create(Material.SKULL_ITEM, (byte)3, fileName.replace ("&", "§")), "http://brainsynder.tk/textures/" + fileName + ".png");
		}

		public static ItemStack convertUrl (String fileName, ChatColor color){
			return convert (create(Material.SKULL_ITEM, (byte)3, color + fileName), "http://brainsynder.tk/textures/" + fileName + ".png");
		}

		public static ItemStack convert(String url){
			return convert (new ItemStack (Material.SKULL_ITEM, 1, (byte)3), url);
		}

		public static ItemStack convert (ItemStack stack, String url){
			return convertToSkinSkull (stack, url, "org.bukkit.craftbukkit.v1_8_R3.inventory.CraftMetaSkull");
		}



		public static ItemStack convertToSkinSkull(ItemStack paramItemStack, String url, String paramString2)
		{
			if (paramItemStack.getType() == Material.SKULL_ITEM)
			{
				SkullMeta localSkullMeta = (SkullMeta)paramItemStack.getItemMeta();
				try
				{
					Class localClass = Class.forName(paramString2);
					Object localObject = localClass.cast(localSkullMeta);
					Field localField = localObject.getClass().getDeclaredField("profile");
					localField.setAccessible(true);
					localField.set(localObject, getNonPlayerProfile(url));
					localSkullMeta = SkullMeta.class.cast(localObject);
					paramItemStack.setItemMeta(localSkullMeta);
				}
				catch (ClassNotFoundException|IllegalArgumentException|IllegalAccessException|NoSuchFieldException|SecurityException localClassNotFoundException)
				{
					localClassNotFoundException.printStackTrace();
				}
			}
			return paramItemStack;
		}

		public static String getLink(ItemStack paramItemStack, String paramString)
		{
			SkullMeta localSkullMeta = (SkullMeta)paramItemStack.getItemMeta();
			try
			{
				Class localClass = Class.forName(paramString);
				Object localObject = localClass.cast(localSkullMeta);
				Field localField = localObject.getClass().getDeclaredField("profile");
				localField.setAccessible(true);
				GameProfile localGameProfile = (GameProfile)localField.get(localObject);
				Collection<Property> localCollection = localGameProfile.getProperties().get("textures");
				for (Property localProperty : localCollection) {
					if (localProperty.getName().equals("textures"))
					{
						String str1 = Base64Coder.decodeString(localProperty.getValue());
						StringBuilder str2 = new StringBuilder();
						int i = 0;
						for (int j = 0; j < str1.length(); j++) {
							if (str1.charAt(j) == '"') {
								i = i != 0 ? 0 : 1;
							} else if (i != 0) {
								str2.append(str1.charAt(j));
							}
						}
						return str2.toString();
					}
				}
			}
			catch (ClassNotFoundException|IllegalArgumentException|IllegalAccessException|NoSuchFieldException|SecurityException localClassNotFoundException)
			{
				localClassNotFoundException.printStackTrace();
			}
			return null;
		}

		private static GameProfile getNonPlayerProfile(String paramString)
		{
			GameProfile localGameProfile = new GameProfile(UUID.randomUUID(), null);
			localGameProfile.getProperties().put("textures", new Property("textures", Base64Coder.encodeString("{textures:{SKIN:{url:\"" + paramString + "\"}}}")));
			return localGameProfile;
		}
	}
}
