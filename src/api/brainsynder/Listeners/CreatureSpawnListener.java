package api.brainsynder.Listeners;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import simple.brainsynder.utils.Valid;

import java.util.HashMap;
import java.util.Map;

public class CreatureSpawnListener implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSpawn(CreatureSpawnEvent e) {
        if (e.getEntity() instanceof Ocelot) {
            if (e.getSpawnReason() == SpawnReason.CUSTOM) {
                e.setCancelled(false);
            }
        }
    }

    public ItemStack opItem(ItemStack item) {
        Valid.notNull(item);
        net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsStack.getTag();
        if (compound == null) {
            compound = new NBTTagCompound();
            nmsStack.setTag(compound);
            compound = nmsStack.getTag();
        }
        if (compound.hasKey("AttributeModifiers"))
            compound.remove("AttributeModifiers");

        ItemStack stack = CraftItemStack.asBukkitCopy(nmsStack);

        Map<Enchantment, Integer> enchantment = new HashMap<>();
        for (Enchantment enchant : stack.getEnchantments().keySet()) {
            int lvl = stack.getEnchantments().get(enchant);
            if (lvl > 6) {
                lvl = 5;
            }
            stack.removeEnchantment(enchant);
            enchantment.put(enchant, lvl);
        }
        for (Enchantment enchant : enchantment.keySet()) {
            int lvl = enchantment.get(enchant);
            stack.addUnsafeEnchantment(enchant, lvl);
        }

        return stack;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack clicked = p.getItemInHand();

        //if (p.isOp()) return;
        if (clicked == null || clicked.getType() == Material.AIR) return;

        ItemStack newItem = opItem(clicked);
        if (!newItem.isSimilar(clicked)) {
            p.setItemInHand(newItem);
            p.updateInventory();
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        ItemStack clicked = e.getCurrentItem();
        Player p = (Player) e.getWhoClicked();

        //if (p.isOp()) return;
        if (clicked == null || clicked.getType() == Material.AIR) return;
        ItemStack newItem = opItem(clicked);
        if (!newItem.isSimilar(clicked)) {
            e.setCurrentItem(newItem);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        ItemStack clicked = e.getItemDrop().getItemStack();
        if (clicked == null || clicked.getType() == Material.AIR) return;
        ItemStack newItem = opItem(clicked);
        if (!newItem.isSimilar(clicked)) {
            e.getItemDrop().setItemStack(newItem);
        }
    }

    @EventHandler
    public void onDrop(BlockDispenseEvent e) {
        ItemStack clicked = e.getItem();
        if (clicked == null || clicked.getType() == Material.AIR) return;
        ItemStack newItem = opItem(clicked);
        if (!newItem.isSimilar(clicked)) {
            e.setItem(newItem);
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        ItemStack clicked = e.getItem().getItemStack();
        if (clicked == null || clicked.getType() == Material.AIR) return;
        ItemStack newItem = opItem(clicked);
        if (!newItem.isSimilar(clicked)) {
            e.getItem().setItemStack(newItem);
        }
    }

    @EventHandler
    public void onPickup(PlayerItemHeldEvent e) {
        ItemStack clicked = e.getPlayer().getInventory().getItem(e.getNewSlot());
        if (clicked == null || clicked.getType() == Material.AIR) return;
        ItemStack newItem = opItem(clicked);
        if (!newItem.isSimilar(clicked)) {
            e.getPlayer().getInventory().setItem(e.getNewSlot(), newItem);
            e.getPlayer().updateInventory();
        }
    }
}