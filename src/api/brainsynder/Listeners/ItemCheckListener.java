package api.brainsynder.Listeners;

import api.brainsynder.nms.ItemHandle;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemCheckListener implements Listener {
    private ItemHandle handle;

    public ItemCheckListener () {
        handle = ItemHandle.ClassGetter.getInstance();
    }


    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack clicked = p.getItemInHand();

        if (clicked == null || clicked.getType() == Material.AIR) return;
        ItemStack newItem = handle.downgradeItem(clicked);
        if (!newItem.isSimilar(clicked)) {
            p.setItemInHand(newItem);
            p.updateInventory();
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        ItemStack clicked = e.getCurrentItem();
        Player p = (Player) e.getWhoClicked();

        if (clicked == null || clicked.getType() == Material.AIR) return;
        ItemStack newItem = handle.downgradeItem(clicked);
        if (!newItem.isSimilar(clicked)) {
            e.setCurrentItem(newItem);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        ItemStack clicked = e.getItemDrop().getItemStack();
        if (clicked == null || clicked.getType() == Material.AIR) return;
        ItemStack newItem = handle.downgradeItem(clicked);
        if (!newItem.isSimilar(clicked)) {
            e.getItemDrop().setItemStack(newItem);
        }
    }

    @EventHandler
    public void onDrop(BlockDispenseEvent e) {
        ItemStack clicked = e.getItem();
        if (clicked == null || clicked.getType() == Material.AIR) return;
        ItemStack newItem = handle.downgradeItem(clicked);
        if (!newItem.isSimilar(clicked)) {
            e.setItem(newItem);
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        ItemStack clicked = e.getItem().getItemStack();
        if (clicked == null || clicked.getType() == Material.AIR) return;
        ItemStack newItem = handle.downgradeItem(clicked);
        if (!newItem.isSimilar(clicked)) {
            e.getItem().setItemStack(newItem);
        }
    }

    @EventHandler
    public void onPickup(PlayerItemHeldEvent e) {
        ItemStack clicked = e.getPlayer().getInventory().getItem(e.getNewSlot());
        if (clicked == null || clicked.getType() == Material.AIR) return;
        ItemStack newItem = handle.downgradeItem(clicked);
        if (!newItem.isSimilar(clicked)) {
            e.getPlayer().getInventory().setItem(e.getNewSlot(), newItem);
            e.getPlayer().updateInventory();
        }
    }
    
    	public void checkItem(Player player) {
		int slot = 0;
		while(slot <= 8) {
			ItemStack loaded = player.getInventory().getItem(slot);
			if(loaded == null || loaded.getType() == Material.AIR) continue;
			ItemStack item = handle.downgradeItem(loaded);
			if(!item.isSimilar(loaded)) {
				player.getInventory().setItem(slot, item);
			}
			slot++;
		}
	}
    
    @EventHandler
    public void onSavedToolBarLoad(InventoryCreativeEvent e) {
    	Player p = (Player) e.getWhoClicked();
    	if(e.getSlotType() == SlotType.QUICKBAR) {
    		checkItem(p);
    	}
    }
}
