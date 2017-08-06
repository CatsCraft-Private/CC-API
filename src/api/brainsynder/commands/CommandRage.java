package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.commands.api.Command;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandRage extends CommandCore {

    @Command(name = "rage")
    public void run (Player p) {
        if (Core.get().isBlockCommands()) {
            p.sendMessage(prefix + "Sorry, Chat Commands are disabled until further notice.");
            return;
        }

        if (!Cooldown.hasCooldown (p)) {
            Cooldown.giveCooldown (p);
            String face;
            int i = randInt (1, 2);
            if(i == 1) {
                face = "(╯‵Д′)╯︵┻━┻";
            } else {
                face = "┻━┻︵ヽ(‵Д′ヽ)";
            }
            Bukkit.getOnlinePlayers()
                    .forEach(player -> player.sendMessage(prefix + p.getName () + " is raging " + face + " *Uggggg*"));
        }
    }
}
