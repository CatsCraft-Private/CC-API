package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.Utils.UtilFlash;
import api.brainsynder.commands.api.Command;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import simple.brainsynder.api.ParticleMaker;

public class CommandSalty extends CommandCore {

    @Command(name = "salty")
    public void run (Player p) {
        if (Core.get().isBlockCommands()) {
            p.sendMessage(prefix + "Sorry, Chat Commands are disabled until further notice.");
            return;
        }

        if (!Cooldown.hasCooldown (p)) {
            Cooldown.giveCooldown (p);
            p.sendMessage(prefix + "Why you so salty?");
            Bukkit.getOnlinePlayers()
                    .stream().filter(players -> (!players.getUniqueId().equals(p.getUniqueId())))
                    .forEach(player -> player.sendMessage(prefix + p.getName() + " is now salty"));
            sendParticle(p, ParticleMaker.Particle.CLOUD, 0.5F, 1.0F, 0.5F);
            UtilFlash.addRed(p);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Core.get(), () -> UtilFlash.removeRed(p), 20 * 10);
        }
    }
}
