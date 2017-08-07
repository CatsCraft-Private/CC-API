package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.commands.api.Command;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class CommandPee extends CommandCore {

    @Command(name = "pee")
    public void run(Player p) {
        if (Core.get().isBlockCommands()) {
            p.sendMessage(prefix + "Sorry, Chat Commands are disabled until further notice.");
            return;
        }

        if (!Cooldown.hasCooldown(p)) {
            Cooldown.giveCooldown(p);
            new BukkitRunnable() {
                int d = 0;

                @Override
                public void run() {
                    if (!p.isOnline()) {
                        cancel();
                        return;
                    }
                    if (d == 200) {
                        cancel();
                        return;
                    }

                    Location loc = p.getLocation();
                    loc.add(0.0D, 0.6D, 0.0D);

                    ItemStack is = new ItemStack(((d < 110) ? Material.GOLD_BLOCK : Material.GOLD_NUGGET), 1);
                    ItemMeta im = is.getItemMeta();
                    im.setDisplayName(String.valueOf(Math.random()));
                    is.setItemMeta(im);

                    final Item fb = p.getWorld().dropItem(loc, is);
                    fb.setPickupDelay(300000);
                    fb.setVelocity(p.getLocation().getDirection().multiply(0.4D).add(new Vector(0.0D, 0.25D, 0.0D)));
                    fb.setMetadata("takeable", new FixedMetadataValue(Core.get(), "takeable"));

                    d += 10;
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            fb.remove();
                        }
                    }.runTaskLater(Core.get(), 13);
                }
            }.runTaskTimer(Core.get(), 1, 3);
        }
    }
}
