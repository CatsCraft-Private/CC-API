package api.brainsynder.Listeners;

import org.bukkit.entity.Ocelot;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public class CreatureSpawnListener implements Listener {
	
	
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onSpawn(CreatureSpawnEvent e) {
		if (e.getEntity() instanceof Ocelot) {
			if (e.getSpawnReason() == SpawnReason.CUSTOM) {
				e.setCancelled(false);
			}
		}
	}
}
