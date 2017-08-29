package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.animation.AnimationCycle;
import api.brainsynder.animation.AnimationManager;
import api.brainsynder.commands.api.Command;
import api.brainsynder.wrappers.ArmorStandWrapper;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

public class CommandDabFest extends CommandCore {


    // THIS COMMAND IS A W.I.P.
    @Command(name = "dabfest")
    public void run(Player p) {
        if (Core.get().isBlockCommands()) {
            p.sendMessage(prefix + "Sorry, Chat Commands are disabled until further notice.");
            return;
        }

        if (!Cooldown.hasCooldown(p)) {
            Core.get().spawnMe = true;
            ArmorStand stand = p.getWorld().spawn(p.getLocation(), ArmorStand.class);
            stand.setArms(true);
            ArmorStandWrapper wrapper = new ArmorStandWrapper(stand);
            AnimationCycle cycle = new AnimationCycle(wrapper, AnimationManager.dab);
            cycle.register(1);
        }
    }
}
