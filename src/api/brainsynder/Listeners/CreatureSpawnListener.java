package api.brainsynder.Listeners;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Ocelot;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class CreatureSpawnListener implements Listener {


	@EventHandler (priority = EventPriority.HIGHEST)
	public void onSpawn(CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Ocelot) {
			if (e.getSpawnReason() == SpawnReason.CUSTOM) {
				e.setCancelled(false);
			}
		}
	}

	@EventHandler
	public void onOpen (InventoryOpenEvent event) {
		ItemStack[] newItems = new ItemStack[event.getInventory().getContents().length];
		List<ItemStack> items = Arrays.asList(event.getInventory().getContents());
		int i = 0;
		for (ItemStack item : items) {
			if (item == null) {
				newItems[i] = new ItemStack(Material.AIR);
				i++;
				continue;
			}
			if (item.getType() == Material.AIR) {
				newItems[i] = new ItemStack(Material.AIR);
				i++;
				continue;
			}

			net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
			NBTTagCompound compound = nmsStack.getTag();
			if (compound == null) {
				compound = new NBTTagCompound();
				nmsStack.setTag(compound);
				compound = nmsStack.getTag();
			}
            if (compound.hasKey("AttributeModifiers"))
                compound.remove("AttributeModifiers");
			nmsStack.setTag(compound);
			ItemStack stack = CraftItemStack.asBukkitCopy(nmsStack);

			// Handles Enchantment levels

			for (Enchantment enchant : stack.getEnchantments().keySet()) {
			    int lvl = stack.getEnchantments().get(enchant);
			    if (lvl > 6) {
			        lvl = 5;
                }
                stack.removeEnchantment(enchant);

                stack.addUnsafeEnchantment(enchant, lvl);
            }

			newItems[i] = stack;

			i++;
		}
		event.getInventory().setContents(newItems);
	}
}
