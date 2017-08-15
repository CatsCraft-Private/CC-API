package api.brainsynder.Listeners;

import api.brainsynder.Core;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class EntityDamageListener implements Listener {
    @EventHandler
    public void onFallDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (e.getCause() == DamageCause.FALL) {
                if (Core.get().getNoFall().contains(p.getUniqueId())) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
