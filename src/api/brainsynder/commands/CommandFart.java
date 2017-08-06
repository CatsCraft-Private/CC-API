package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.commands.api.Command;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import simple.brainsynder.api.ParticleMaker;

import java.util.ArrayList;
import java.util.List;

public class CommandFart extends CommandCore {

    @Command(name = "fart")
    public void run (Player p) {
        if (Core.get().isBlockCommands()) {
            p.sendMessage(prefix + "Sorry, Chat Commands are disabled until further notice.");
            return;
        }

        if (!Cooldown.hasCooldown (p)) {
            Cooldown.giveCooldown (p);
            Location location = p.getLocation().clone().add(0, 0.8, 0);
            double distance = 0.3d;
            double yawRadians = Math.PI * location.getYaw() / 180;
            location.add(distance * Math.sin(yawRadians), 0, -distance * Math.cos(yawRadians));
            ParticleMaker maker = new ParticleMaker(ParticleMaker.Particle.SPELL_INSTANT, 50, 0.1);
            maker.sendToLocation(location);
    
            List<Player> hasEffect = new ArrayList<>();
            new BukkitRunnable() {
                @Override public void run() {
                    PotionEffect effect = new PotionEffect(PotionEffectType.CONFUSION, Integer.MAX_VALUE, 9, true, true);
                    effect.apply(p);
                    hasEffect.add(p);
                    p.sendMessage("§eCatsCraft §6§l>> §7*SNIFF SNIFF* Should not have had beans for lunch...");
                    List<Entity> nearby = p.getNearbyEntities(10, 10, 10);
                    for (Entity ent : nearby) {
                        if (ent instanceof Player) {
                            Player player = (Player) ent;
                            if (!player.getUniqueId().toString().equals(p.getUniqueId().toString())) {
                                player.sendMessage("§eCatsCraft §6§l>> §7*SNIFF SNIFF* Who farted?");
                                hasEffect.add(player);
                                effect.apply(player);
                            }
                        }
                    }
            
                    new BukkitRunnable() {
                        @Override public void run() {
                            hasEffect.stream()
                                    .filter(player -> player.hasPotionEffect(PotionEffectType.CONFUSION))
                                    .forEach(player -> player.removePotionEffect(PotionEffectType.CONFUSION));
                        }
                    }.runTaskLater(Core.get(), 60);
                }
            }.runTaskLater(Core.get(), 4);
        }
    }
}
