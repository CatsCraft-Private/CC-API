package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.commands.api.Command;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandFurBall extends CommandCore {

    @Command(name = "furball")
    public void run(Player p) {
        if (Core.get().isBlockCommands()) {
            p.sendMessage(prefix + "Sorry, Chat Commands are disabled until further notice.");
            return;
        }
        if (!Cooldown.hasCooldown(p)) {
            Bukkit.getOnlinePlayers().forEach(Player -> Player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eCatsCraft &6>> " + p.getName() + " &7just coughed up a furball :O")));
			ItemStack toDrop = new ItemStack(Material.FIREWORK_CHARGE);
			ItemMeta toDropMeta = toDrop.getItemMeta();
			toDropMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Furball"));
			toDrop.setItemMeta(toDropMeta);
			p.getWorld().dropItem(p.getLocation(), toDrop);
            Cooldown.giveCooldown(p);
        }
    }
}
