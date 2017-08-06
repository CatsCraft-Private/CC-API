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
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.meta.ItemMeta;

@Deprecated
public class EggListener implements Listener {

	@EventHandler
	public void onPlace (BlockPlaceEvent e){
		if (e.getBlockPlaced ().getType ().equals (Material.SKULL)) {
			if (e.getItemInHand ().hasItemMeta ()){
				ItemMeta meta = e.getItemInHand ().getItemMeta ();
				if (meta.hasDisplayName ()) {
					if (e.getItemInHand ().getType ().equals (Material.SKULL_ITEM)) {
                        if (!meta.getDisplayName ().startsWith ("Easter Egg: "))
                            return;
						String[] args = meta.getDisplayName ().split (" ");
						int id = Integer.parseInt (args[2]);
						Skull skull = (Skull)e.getBlockPlaced ().getState ();
						if (MainEggHunt.eggs.containsKey (skull)) {
							e.setCancelled (true);
							e.getPlayer ().sendMessage ("§e§lCatsCraft §6§l>> §7This egg is already placed.");
							return;
						}
						e.getPlayer ().sendMessage ("§e§lCatsCraft §6§l>> §7EasterEgg has been placed.");
						e.getItemInHand ().setType (Material.AIR);
						e.getPlayer ().updateInventory ();
						String main = "Eggs." + id + '.';
						Location loc = e.getBlockPlaced ().getLocation ();
						EggFile.setNull (main + "Hint", "Change Hint");
						EggFile.set (main + "Location.World", loc.getWorld ().getName ());
						EggFile.set (main + "Location.X", loc.getX ());
						EggFile.set (main + "Location.Y", loc.getY ());
						EggFile.set (main + "Location.Z", loc.getZ ());
						String hint = EggFile.getString (main + "Hint");
						MainEggHunt.eggs.put (skull, new EasterEgg (skull, id, hint));
					}
				}
			}
		}
	}

	@EventHandler
	public void onInteract (PlayerInteractEvent e) {
		Player p = e.getPlayer ();
		if (e.getAction ().equals (Action.RIGHT_CLICK_BLOCK)) {
			Block block = e.getClickedBlock ();
			if (block.getType ().equals (Material.SKULL)) {
				Skull skull = (Skull)block.getState ();
				if (MainEggHunt.eggs.containsKey (skull)) {
					EasterEgg egg = MainEggHunt.eggs.get (skull);
					if (MainEggHunt.playerFinder.containsKey (p.getName ())) {
						PlayerFinder finder = MainEggHunt.playerFinder.get (p.getName ());
						if (!finder.getEggIds ().contains (String.valueOf(egg.getId()))) {
							if (finder.isNextEgg (egg)) {
								finder.addFound (egg);
								if (!finder.isOnLast ()) {
									for (String s : EggFile.getList ("Reward-Commands-For-Not-Last-Egg")) {
										Bukkit.getServer ().dispatchCommand (Bukkit.getConsoleSender (), s.replace ("%player%", p.getName ()));
									}
									p.sendMessage ("§e§lCatsCraft §6§l>> §7You have found an EasterEgg, Here is your next hint");
									p.sendMessage ("§e§lEgg Hint §6§l>> §c" + finder.getNextHint ());
								}else{
									p.sendMessage ("§e§lCatsCraft §6§l>> §7You have found all the EasterEggs, You have been awarded prizes!");
									for (String s : EggFile.getList ("Reward-Commands-For-Last-Egg")) {
										Bukkit.getServer ().dispatchCommand (Bukkit.getConsoleSender (), s.replace ("%player%", p.getName ()));
									}
									for (Player players : Bukkit.getOnlinePlayers ()) {
										if (!players.getName ().equals (p.getName ())) {
											players.sendMessage ("§e§lCatsCraft §6§l>> §7" + p.getName () + " has found all the EasterEggs!!!");
										}
									}
								}
							}else{
								p.sendMessage ("§e§lCatsCraft §6§l>> §7You have not found the egg before this one, here is a hint.");
								p.sendMessage ("§e§lEgg Hint §6§l>> §c" + finder.getNextHint ());
							}
						}else{
							if (finder.isOnLast ()) {
								p.sendMessage ("§e§lCatsCraft §6§l>> §7You have already found all the EasterEggs.");
								return;
							}
							p.sendMessage ("§e§lCatsCraft §6§l>> §7You have already found that egg.");
							p.sendMessage ("§e§lEgg Hint §6§l>> §c" + finder.getNextHint ());
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onKick (PlayerKickEvent e) {
		Player p = e.getPlayer ();
		MainEggHunt.savePlayer (p);
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent e){
		Player p = e.getPlayer ();
		MainEggHunt.savePlayer (p);
	}

	@EventHandler
	public void onJoin (PlayerJoinEvent e) {
		final Player p = e.getPlayer ();
		MainEggHunt.playerFinder.put (p.getName (), new PlayerFinder (p));
	}
}
