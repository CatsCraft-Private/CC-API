package api.brainsynder.Listeners;

import api.brainsynder.Core;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class CreatureSpawnListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        Entity e = event.getEntity();
        if(Core.get().spawnMe) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCreatureSpawnUnBlock(CreatureSpawnEvent event) {
        Entity e = event.getEntity();
        if(Core.get().spawnMe && event.isCancelled()) {
            event.setCancelled(false);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onCreatureSpawn(EntitySpawnEvent event) {
        Entity e = event.getEntity();
        if(Core.get().spawnMe) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCreatureSpawnUnBlock(EntitySpawnEvent event) {
        Entity e = event.getEntity();
        if(Core.get().spawnMe && event.isCancelled()) {
            event.setCancelled(false);
        }
    }
}