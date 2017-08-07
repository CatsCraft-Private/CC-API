package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.commands.api.Command;
import org.bukkit.entity.Player;

public class CommandSetServer extends CommandCore {

    @Command(name = "setserver", arguments = "*")
    public void run(Player p, String[] args) {
        if (allowed.contains(p.getUniqueId().toString())) {
            if (args.length == 0) {
                p.sendMessage("Please supply a server name");
            } else {
                Core.get().getConfiguration().set("ServerName", args[0]);
            }
        }
    }
}
