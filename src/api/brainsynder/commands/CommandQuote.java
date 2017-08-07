package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.commands.api.Command;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class CommandQuote extends CommandCore {

    @Command(name = "quotes")
    public void quotes(Player commandSender, String[] args) {
        if (args.length == 0) {
            if (!Core.get().quotes.isEmpty()) {
                Random r = new Random();
                int a = r.nextInt(Core.get().quotes.size());
                commandSender.sendMessage("§eTotal Quotes§6: §7" + Core.get().quotes.size());
                commandSender.sendMessage("§eQuoteNumber§6: §7" + (a + 1));
                commandSender.sendMessage("§eQuote §6: §7" + Core.get().quotes.get(a));
            } else {
                commandSender.sendMessage("§eThere are no quotes configured.. Check back later..");
            }
        } else if (args[0].equalsIgnoreCase("reload")) {
            Core.get().reloadConfig();
            Core.get().quotes = Core.get().getConfig().getStringList("Insperational-quotes");
            (new BukkitRunnable() {
                public void run() {
                    if (!Core.get().quotes.isEmpty()) {
                        Random r = new Random();
                        int a = r.nextInt(Core.get().quotes.size());
                        commandSender.sendMessage("§eTotal Quotes§6: §7" + Core.get().quotes.size());
                        commandSender.sendMessage("§eQuoteNumber§6: §7" + (a + 1));
                        commandSender.sendMessage("§eQuote §6: §7" + Core.get().quotes.get(a));
                    } else {
                        commandSender.sendMessage("§eThere are no quotes configured.. Check back later..");
                    }

                }
            }).runTaskLater(Core.get(), 2L);
        } else if (Integer.parseInt(args[0]) >= 1 && Integer.parseInt(args[0]) <= Core.get().quotes.size()) {
            int i = Integer.parseInt(args[0]);
            commandSender.sendMessage("§eTotal Quotes§6: §7" + Core.get().quotes.size());
            commandSender.sendMessage("§eQuoteNumber§6: §7" + i);
            commandSender.sendMessage("§eQuote §6: §7" + Core.get().quotes.get(i - 1));
        } else {
            commandSender.sendMessage("§eInvalid quote number. Use numbers 1 to " + Core.get().quotes.size());
        }
    }
}
