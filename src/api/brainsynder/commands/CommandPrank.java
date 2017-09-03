package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.commands.api.Command;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import simple.brainsynder.sound.SoundMaker;
import simple.brainsynder.utils.Reflection;

public class CommandPrank extends CommandCore {
	
	
	@Command(name = "prank")
	public void run(Player p, String[] args) {
        if (Core.get().isBlockCommands()) {
            p.sendMessage(prefix + "Sorry, Chat Commands are disabled until further notice.");
            return;
        }

        if (args.length == 0) {
            p.sendMessage(this.prefix + "/prank <player>");
        }else{
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                p.sendMessage(prefix + "Player could not be found.");
                return;
            }

            if(!Cooldown.hasCooldown(p, 20)) {
                Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[&e&lMP&6] " + p.getName() + " &7Pranked &6" + target.getName())));
                Cooldown.giveCooldown(p);

                SoundMaker.ENTITY_GENERIC_EXPLODE.playSound(target);
                Reflection.getTitleMessage().sendMessage(target, 0, 2, 0, "§4§lBOOO");
            }
        }
	}
}
