package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.commands.api.Command;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import simple.brainsynder.sound.SoundMaker;

public class CommandSpleef extends CommandCore {
    
    @Command(name = "spleef")
    public void run(Player p, String[] args) {
        if (Core.get().isBlockCommands()) {
            p.sendMessage(prefix + "Sorry, Chat Commands are disabled until further notice.");
            return;
        }

        if (!Cooldown.hasCooldown(p)) {
            if (args.length == 0) {
                p.sendMessage(prefix + "/spleef <player>");
                return;
            }
            final Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                p.sendMessage(prefix + args[0] + " is not online or not correct.");
                return;
            }
            Cooldown.giveCooldown(p);
            Bukkit.getOnlinePlayers()
                    .forEach(player -> player.sendMessage(prefix + p.getName() + " SPLEEEEEEFED " + target.getName()));
            timedDelay(p, Material.DIAMOND_SPADE, 20 * 3);
            sendParticle(target, Material.SNOW_BLOCK, 0.5F, 0.5F, 0.5F);
            SoundMaker.BLOCK_SNOW_STEP.playSound(p.getLocation(), 1.5F, 1.5F);
            
        }
    }
}
