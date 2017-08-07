package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.commands.api.Command;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandComfort extends CommandCore {

    @Command(name = "comfort")
    public void run(Player p, String[] args) {
        if (Core.get().isBlockCommands()) {
            p.sendMessage(prefix + "Sorry, Chat Commands are disabled until further notice.");
            return;
        }

        if (args.length == 0) {
            p.sendMessage(prefix + "/comfort <player>");
            return;
        }
        final Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            p.sendMessage(args[0] + " is not online or not correct.");
            return;
        }
        if (!Cooldown.hasCooldown(p)) {
            Cooldown.giveCooldown(p);
            Bukkit.getOnlinePlayers()
                    .forEach(player -> player.sendMessage(prefix + p.getName() + ": (>_<)＼(•.•) It's ok, I'm here " + target.getName()));
        }
    }
}
