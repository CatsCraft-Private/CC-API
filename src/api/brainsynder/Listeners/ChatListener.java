/*
 * Copyright (c) created class file on: 2016.
 * All rights reserved.
 * Copyright owner: brainsynder/Magnus498
 * To contact the developer go to:
 * - spigotmc.org and look up brainsynder
 * - email at: briansnyder498@gmail.com
 * - or Skype at live:starwars4393
 */

package api.brainsynder.Listeners;

import api.brainsynder.Core;
import api.brainsynder.Utils.RainbowLetters;
import api.brainsynder.Utils.RainbowWords;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import java.util.ArrayList;
import java.util.List;

public class ChatListener implements Listener {

    @EventHandler
    public void onBRB(AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        if (e.getMessage().equalsIgnoreCase("brb") || e.getMessage().equalsIgnoreCase("i'll be right back") || e.getMessage().equalsIgnoreCase("ill be right back") || e.getMessage().equalsIgnoreCase("be right back")) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.get(), () -> p.performCommand("afk"), 20L);
        }
    }

    @EventHandler
    public void pick(PlayerPickupItemEvent e) {
        if (e.getItem().hasMetadata("takeable")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void pick(InventoryPickupItemEvent e) {
        if (e.getItem().hasMetadata("takeable")) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void commandBlock(PlayerCommandPreprocessEvent e) {
        final Player p = e.getPlayer();
        String[] args = e.getMessage().split(" ");
        String command = args[0];
        if (command.startsWith("/")) {
            command = command.replaceFirst("/", "");
            if (Core.get().getConfig().getStringList("Commands-To-Have-Cooldown").contains(command)) {
                if (!p.hasPermission("CatsCraft.cooldown.bypass")) {
                    if (CommandCooldown.hasCooldown(p)) {
                        e.setCancelled(true);
                        return;
                    }
                    CommandCooldown.giveCooldown(p);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void commandBlock(AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        String[] args = e.getMessage().split(" ");
        String command = args[0];
        if (command.startsWith("/")) {
            command = command.replaceFirst("/", "");
            if (Core.get().getConfig().getStringList("Commands-To-Have-Cooldown").contains(command)) {
                if (!p.hasPermission("CatsCraft.cooldown.bypass")) {
                    if (CommandCooldown.hasCooldown(p)) {
                        e.setCancelled(true);
                        return;
                    }
                    CommandCooldown.giveCooldown(p);
                }
            }
        }
    }

    @EventHandler
    public void callOutPlayer(AsyncPlayerChatEvent e) {
        String msg = e.getMessage();
        if (e.getMessage().startsWith("$r")) {
            if (e.getPlayer().hasPermission("CatsCraft.rainbowLetters")) {
                msg = msg.replace("$r", "");
                RainbowLetters text = new RainbowLetters(msg);
                msg = text.getText();
            }
        }
        if (e.getMessage().startsWith("$w")) {
            if (e.getPlayer().hasPermission("CatsCraft.rainbowWords")) {
                msg = msg.replace("$w", "");
                RainbowWords text = new RainbowWords(msg);
                msg = text.getText();
            }
        }
        if (e.getMessage().startsWith("$s")) {
            if (e.getPlayer().hasPermission("CatsCraft.scramble")) {
                msg = msg.replace("$s", "");
                msg = scramble(msg);
            }
        }
        for (Player players : Bukkit.getServer().getOnlinePlayers()) {
            String playerName = players.getName();
            if (e.getMessage().contains(playerName)) {
                String color = ChatColor.RESET.toString();
                try {
                    String[] args = e.getMessage().split(playerName);
                    color = ChatColor.getLastColors(args[0]);
                }catch (Throwable ignored) {}
                msg = msg.replaceAll(playerName, ChatColor.DARK_AQUA + "@" + playerName + color);
            }
        }
        e.setMessage(msg);
    }

    @EventHandler
    public void onSign(SignChangeEvent e) {
        int i = 0;
        for (String msg : e.getLines()) {

            if (msg.startsWith("$r")) {
                if (e.getPlayer().hasPermission("CatsCraft.rainbowLetters")) {
                    msg = msg.replace("$r", "");
                    RainbowLetters text = new RainbowLetters(msg);
                    msg = text.getText();
                }
            }
            if (msg.startsWith("$w")) {
                if (e.getPlayer().hasPermission("CatsCraft.rainbowWords")) {
                    msg = msg.replace("$w", "");
                    RainbowWords text = new RainbowWords(msg);
                    msg = text.getText();
                }
            }
            if (msg.startsWith("$s")) {
                if (e.getPlayer().hasPermission("CatsCraft.scramble")) {
                    msg = msg.replace("$s", "");
                    msg = scramble(msg);
                }
            }
            e.setLine(i, msg);
            i++;
        }
    }

    public String scramble(String input) {
        StringBuilder out = new StringBuilder();
        for (String part : input.split(" ")) {
            List<Character> characters = new ArrayList<>();
            for (char c : part.toCharArray()) {
                characters.add(c);
            }
            StringBuilder output = new StringBuilder(part.length());
            while (characters.size() != 0) {
                int rndm = (int) (Math.random() * characters.size());
                output.append(characters.remove(rndm));
            }
            out.append(output.toString()).append(' ');
        }
        return out.toString().trim();
    }

    @EventHandler
    public void onInvPickup(InventoryPickupItemEvent e) {
        if (e.getInventory().getType() == InventoryType.HOPPER) {
            if (e.getItem().hasMetadata("takeable")) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEdit(PlayerArmorStandManipulateEvent e) {
        if (e.getRightClicked().hasMetadata("NO_TOUCH")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    private void onSpawn(EntitySpawnEvent e) {
        if (e.isCancelled()) {
            if (e.getEntity() instanceof ArmorStand) {
                if (Core.get().spawnMe
                        && e.getEntity().hasMetadata("Spawnable")) {
                    e.setCancelled(false);
                    Core.get().spawnMe = false;
                }
            }
        }
    }
}
