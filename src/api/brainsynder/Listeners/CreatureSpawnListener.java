package api.brainsynder.Listeners;

import net.minecraft.server.v1_8_R3.NBTTagCompound;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CreatureSpawnListener implements Listener {


	@EventHandler (priority = EventPriority.HIGHEST)
	public void onSpawn(CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Ocelot) {
			if (e.getSpawnReason() == SpawnReason.CUSTOM) {
				e.setCancelled(false);
			}
		}
	}
	
	public boolean opItem(ItemStack item) {
		net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
		NBTTagCompound compound = nmsStack.getTag();
		if (compound == null) {
			compound = new NBTTagCompound();
			nmsStack.setTag(compound);
			compound = nmsStack.getTag();
		}
        if (compound.hasKey("AttributeModifiers")) {
            compound.remove("AttributeModifiers");
            return true;
        }
		ItemStack stack = CraftItemStack.asBukkitCopy(nmsStack);

		for (Enchantment enchant : stack.getEnchantments().keySet()) {
		    int lvl = stack.getEnchantments().get(enchant);
		    if (lvl > 6) {
		    	return true;
            }
        }
		return false;
	}
	
	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack clicked = p.getItemInHand();
		
		if(p.isOp()) return;
		if(clicked == null || clicked.getType() == Material.AIR) return;
				
		if(opItem(clicked)) {
			e.setUseItemInHand(Result.DENY);
			e.setCancelled(true);
			clicked.setType(Material.AIR);
			p.updateInventory();
			return;
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		ItemStack clicked = e.getCurrentItem();
		ItemStack onMouse = e.getCursor();
		Player p = (Player) e.getWhoClicked();
		
		if(p.isOp()) return;
		if(clicked == null || onMouse == null) return;
		
		if(clicked.getType()  != Material.AIR && opItem(clicked)) {
			e.setCancelled(true);
			e.setCurrentItem(null);
			return;
		}
		if(onMouse.getType() != Material.AIR && opItem(onMouse)) {
			e.setCancelled(true);
			e.setCurrentItem(null);
			return;
		}
	}
}