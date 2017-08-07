package api.brainsynder.commands;

import api.brainsynder.commands.api.Command;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import simple.brainsynder.wrappers.MaterialWrapper;

import java.util.ArrayList;
import java.util.List;

public class CommandArmorSee extends CommandCore {
    private List<String> itemData(ItemStack item) {
        List<String> data = new ArrayList();
        if (item != null) {
            data.add("&eItem: &6[ &f" + MaterialWrapper.fromMaterial(item.getType()).getId() + "&6:&f" + item.getDurability() + " &6/ &f" + item.getType().toString().replace("_", " ") + " &6]");
            if (item.hasItemMeta()) {
                ItemMeta meta = item.getItemMeta();
                if (meta.hasDisplayName()) {
                    data.add("&eItemName: &6[ &r" + meta.getDisplayName() + " &6]");
                } else {
                    data.add("&eItemName: &6[ &fNo Custom Name &6]");
                }

                if (meta.hasLore()) {
                    data.add("&eItemLore: ");
                    int i = 1;
                    for (String s : meta.getLore()) {
                        data.add("   &eLine: &6" + i + "  &r" + s);
                        i++;
                    }
                } else {
                    data.add("&eItemLore: &6[ &fNo Lore &6]");
                }
            }
            if (!item.getEnchantments().isEmpty()) {
                data.add("&eEnchantments: ");
                for (Enchantment enchant : item.getEnchantments().keySet()) {
                    int lvl = item.getEnchantments().get(enchant);
                    data.add("   &e" + enchant.getName() + ": &6" + lvl);
                }
            } else {
                data.add("&eEnchantments: &6[ &fNo Enchantments &6]");
            }
        } else {
            data.add("&eItem: &6[ &fAir/Null &6]");
        }
        return data;
    }

    @Command(name = "armorsee")
    public void armorSee(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(this.prefix + "/armorsee <player>");
            return;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            player.sendMessage(args[0] + " is not online or not correct.");
            return;
        }

        player.sendMessage(prefix + target.getName() + "'s Helmet §m---------");

        for (String string : itemData(target.getInventory().getHelmet())) {
            player.sendMessage("   " + ChatColor.translateAlternateColorCodes('&', string));
        }
        player.sendMessage(prefix + target.getName() + "'s ChestPlate §m---------");
        for (String string : itemData(target.getInventory().getChestplate())) {
            player.sendMessage("   " + ChatColor.translateAlternateColorCodes('&', string));
        }

        player.sendMessage(prefix + target.getName() + "'s Leggings §m---------");
        for (String string : itemData(target.getInventory().getLeggings())) {
            player.sendMessage("   " + ChatColor.translateAlternateColorCodes('&', string));
        }

        player.sendMessage(prefix + target.getName() + "'s Boots §m---------");
        for (String string : itemData(target.getInventory().getBoots())) {
            player.sendMessage("   " + ChatColor.translateAlternateColorCodes('&', string));
        }
    }
}
