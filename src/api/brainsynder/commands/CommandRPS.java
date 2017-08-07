package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.commands.api.Command;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandRPS extends CommandCore {

    @Command(name = "rps")
    public void run(Player p) {
        if (Core.get().isBlockCommands()) {
            p.sendMessage(prefix + "Sorry, Chat Commands are disabled until further notice.");
            return;
        }

        if (!Cooldown.hasCooldown(p)) {
            Cooldown.giveCooldown(p);
            String hand = getHand();
            Bukkit.getOnlinePlayers()
                    .forEach(player -> player.sendMessage(prefix + p.getName() + " did RockPaperScissors and got: §c" + hand));
        }
    }

    private String getHand() {
        int i = randInt(1, 3);
        if (i == 1) {
            return "Rock";
        } else if (i == 2) {
            return "Paper";
        } else {
            return "Scissors";
        }
    }
}
