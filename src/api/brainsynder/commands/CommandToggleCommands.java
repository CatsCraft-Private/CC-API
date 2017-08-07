package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.commands.api.Command;
import org.bukkit.entity.Player;

public class CommandToggleCommands extends CommandCore {

    @Command(name = "togglecommands")
    public void run(Player p) {
        if (allowed.contains(p.getUniqueId().toString())) {
            Core.get().setBlockCommands(!Core.get().isBlockCommands());
            p.sendMessage(prefix + "Toggled Chat Commands Activated: §e" + !Core.get().isBlockCommands());
        }
    }
}
