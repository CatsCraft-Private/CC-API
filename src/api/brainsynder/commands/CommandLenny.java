package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.commands.api.Command;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandLenny extends CommandCore {

    @Command(name = "lenny")
    public void run(Player p) {
        if (Core.get().isBlockCommands()) {
            p.sendMessage(prefix + "Sorry, Chat Commands are disabled until further notice.");
            return;
        }

        if (!Cooldown.hasCooldown(p)) {
            Cooldown.giveCooldown(p);
            Bukkit.getOnlinePlayers()
                    .forEach(player -> player.sendMessage(prefix + "Lenny is looking at " + p.getName() + " §c( ° ‿ʖ °)"));
        }
    }
}
