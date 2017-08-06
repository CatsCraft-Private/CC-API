package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.commands.api.Command;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import simple.brainsynder.api.ParticleMaker;

public class CommandCry extends CommandCore {

    @Command(name = "icry")
    public void run (Player p) {
        if (Core.get().isBlockCommands()) {
            p.sendMessage(prefix + "Sorry, Chat Commands are disabled until further notice.");
            return;
        }

        if (!Cooldown.hasCooldown (p)) {
            Cooldown.giveCooldown (p);
            p.sendMessage (prefix + "I cry every time :'(");
            Bukkit.getOnlinePlayers()
                    .forEach(player -> player.sendMessage(prefix + p.getName () + " is now crying :'("));
            sendParticle (p, ParticleMaker.Particle.DRIP_WATER, 0.5F, 1.0F, 0.5F);
        }
    }
}
