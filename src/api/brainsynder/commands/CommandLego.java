package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.commands.api.Command;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import simple.brainsynder.sound.SoundMaker;

public class CommandLego extends CommandCore {

    @Command(name = "lego")
    public void run(Player p) {
        if (Core.get().isBlockCommands()) {
            p.sendMessage(prefix + "Sorry, Chat Commands are disabled until further notice.");
            return;
        }

        if (!Cooldown.hasCooldown(p)) {
            Cooldown.giveCooldown(p);
            SoundMaker.BLOCK_GLASS_BREAK.playSound(p.getLocation());
            Bukkit.getOnlinePlayers()
                    .forEach(player -> player.sendMessage(prefix + p.getName() + " was building... when they stepped on a Lego §c*AHHHHHH*"));
            new BukkitRunnable() {
                @Override
                public void run() {
                    SoundMaker.ENTITY_GHAST_SCREAM.playSound(p.getLocation());
                }
            }.runTaskLater(Core.get(), 10);
        }
    }
}
