package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.commands.api.Command;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import simple.brainsynder.nms.IActionMessage;
import simple.brainsynder.utils.Reflection;

public class CommandDabAttack extends CommandCore {

    @Command(name = "dabattack")
    public void run(Player p) {
        if (Core.get().isBlockCommands()) {
            p.sendMessage(prefix + "Sorry, Chat Commands are disabled until further notice.");
            return;
        }

        if (!Cooldown.hasCooldown(p)) {
            if (Core.get().isBlockDabAttack()) {
                p.sendMessage(prefix + "Someone is already having a DabAttack");
                return;
            }
            Cooldown.giveCooldown(p);
            Core.get().setBlockDabAttack(true);
            Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(prefix + p.getName() + " is having a DabAttack."));
            new BukkitRunnable() {
                boolean var = false;
                int count = 0;

                @Override
                public void run() {
                    IActionMessage message = Reflection.getActionMessage();
                    if (!p.isOnline()) {
                        Core.get().setBlockDabAttack(false);
                        cancel();
                        return;
                    }
                    if (count >= 5) {
                        Core.get().setBlockDabAttack(false);
                        cancel();
                        Bukkit.getOnlinePlayers().forEach(player -> message.sendMessage(player, " "));
                        return;
                    }
                    StringBuilder builder = new StringBuilder();
                    builder.append("§b§l");
                    if (var) {
                        builder.append("<o/");
                    } else {
                        builder.append("\\\\").append("o>");
                    }
                    Bukkit.getOnlinePlayers().forEach(player -> message.sendMessage(player, builder.toString()));
                    count++;
                    var = !var;
                }
            }.runTaskTimer(Core.get(), 0, 10);
        }
    }
}
