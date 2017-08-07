package api.brainsynder.commands;

import api.brainsynder.Utils.Cooldown;
import api.brainsynder.commands.api.Command;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ScriptCommands extends CommandCore {

    // TP
    @Command(name = "hug")
    public void hug(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(prefix + "/hug <player>");
        } else {
            if (!Cooldown.hasCooldown(player, 10, "§5[§d§lTP§5]§5 You have to wait {secondsLeft} before you can use this command again!")) {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    player.sendMessage(prefix + args[0] + " is not online or not correct.");
                    return;
                }
                Cooldown.giveCooldown(player);

                Bukkit.getOnlinePlayers().forEach(all ->
                        all.sendMessage("§5[§d§lTP§5]§5 " + player.getName() + " §5hugs " + target.getName() + "§7 Awww!§d<3")
                );
            }
        }
    }

    @Command(name = "shake")
    public void shake(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(prefix + "/shake <player>");
        } else {
            if (!Cooldown.hasCooldown(player, 10, "§5[§d§lTP§5]§5 You have to wait {secondsLeft} before you can use this command again!")) {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    player.sendMessage(prefix + args[0] + " is not online or not correct.");
                    return;
                }
                Cooldown.giveCooldown(player);

                Bukkit.getOnlinePlayers().forEach(all ->
                        all.sendMessage("§5[§d§lTP§5]§5 " + player.getName() + " §5shakes hands with " + target.getName() + "§7 Good Game!")
                );
            }
        }
    }

    @Command(name = "highfive")
    public void highfive(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(prefix + "/highfive <player>");
        } else {
            if (!Cooldown.hasCooldown(player, 10, "§5[§d§lTP§5]§5 You have to wait {secondsLeft} before you can use this command again!")) {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    player.sendMessage(prefix + args[0] + " is not online or not correct.");
                    return;
                }
                Cooldown.giveCooldown(player);

                Bukkit.getOnlinePlayers().forEach(all ->
                        all.sendMessage("§5[§d§lTP§5]§5 " + player.getName() + " §5High Fives " + target.getName() + "§7 <o/")
                );
            }
        }
    }

    @Command(name = "ty")
    public void ty(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(prefix + "/ty <player>");
        } else {
            if (!Cooldown.hasCooldown(player, 10, "§5[§d§lTP§5]§5 You have to wait {secondsLeft} before you can use this command again!")) {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    player.sendMessage(prefix + args[0] + " is not online or not correct.");
                    return;
                }
                Cooldown.giveCooldown(player);

                Bukkit.getOnlinePlayers().forEach(all ->
                        all.sendMessage("§5[§d§lTP§5]§5 " + player.getName() + " §5Thanks " + target.getName())
                );
            }
        }
    }

    @Command(name = "yw")
    public void yw(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(prefix + "/yw <player>");
        } else {
            if (!Cooldown.hasCooldown(player, 10, "§5[§d§lTP§5]§5 You have to wait {secondsLeft} before you can use this command again!")) {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    player.sendMessage(prefix + args[0] + " is not online or not correct.");
                    return;
                }
                Cooldown.giveCooldown(player);

                Bukkit.getOnlinePlayers().forEach(all ->
                        all.sendMessage("§5[§d§lTP§5]§5 " + player.getName() + " §5You're welcome! §7to " + target.getName())
                );
            }
        }
    }

    @Command(name = "poke")
    public void poke(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(prefix + "/poke <player>");
        } else {
            if (!Cooldown.hasCooldown(player, 10, "§5[§d§lTP§5]§5 You have to wait {secondsLeft} before you can use this command again!")) {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    player.sendMessage(prefix + args[0] + " is not online or not correct.");
                    return;
                }
                Cooldown.giveCooldown(player);

                Bukkit.getOnlinePlayers().forEach(all ->
                        all.sendMessage("§5[§d§lTP§5]§5 " + player.getName() + " §5Pokes " + target.getName() + "§7 *Boop!*")
                );
            }
        }
    }

    @Command(name = "wb")
    public void wb(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(prefix + "/wb <player>");
        } else {
            if (!Cooldown.hasCooldown(player, 10, "§5[§d§lTP§5]§5 You have to wait {secondsLeft} before you can use this command again!")) {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    player.sendMessage(prefix + args[0] + " is not online or not correct.");
                    return;
                }
                Cooldown.giveCooldown(player);

                Bukkit.getOnlinePlayers().forEach(all ->
                        all.sendMessage("§5[§d§lTP§5]§5 " + player.getName() + " §7Says §5Welcome back! §7to §5" + target.getName())
                );
            }
        }
    }


    @Command(name = "gg")
    public void gg(Player player, String[] args) {
        if (!Cooldown.hasCooldown(player, 10, "§5[§d§lTP§5]§5 You have to wait {secondsLeft} before you can use this command again!")) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(prefix + args[0] + " is not online or not correct.");
                return;
            }
            Cooldown.giveCooldown(player);

            Bukkit.getOnlinePlayers().forEach(all ->
                    all.sendMessage("§5[§d§lTP§5]§5 " + player.getName() + " §5 says §dGG!")
            );
        }
    }

    @Command(name = "dab")
    public void dab(Player player, String[] args) {
        if (!Cooldown.hasCooldown(player, 10, "§5[§d§lTP§5]§5 You have to wait {secondsLeft} before you can use this command again!")) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(prefix + args[0] + " is not online or not correct.");
                return;
            }
            Cooldown.giveCooldown(player);

            Bukkit.getOnlinePlayers().forEach(all ->
                    all.sendMessage("§5[§d§lTP§5]§5 " + player.getName() + " §7is dabbing! <o/")
            );
        }
    }

    @Command(name = "hugspree")
    public void hugspree(Player player, String[] args) {
        if (!Cooldown.hasCooldown(player, 10, "§5[§d§lTP§5]§5 You have to wait {secondsLeft} before you can use this command again!")) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(prefix + args[0] + " is not online or not correct.");
                return;
            }
            Cooldown.giveCooldown(player);

            Bukkit.getOnlinePlayers().forEach(all ->
                    all.sendMessage("§5[§d§lTP§5]§5 " + player.getName() + " §7hugs §bEveryone! §7AWWW §b❤")
            );
        }
    }

    @Command(name = "dabdash")
    public void dabdash(Player player, String[] args) {
        if (!Cooldown.hasCooldown(player, 10, "§5[§d§lTP§5]§5 You have to wait {secondsLeft} before you can use this command again!")) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(prefix + args[0] + " is not online or not correct.");
                return;
            }
            Cooldown.giveCooldown(player);

            Bukkit.getOnlinePlayers().forEach(all ->
                    all.sendMessage("§5[§d§lTP§5]§5 " + player.getName() + " §7is dab dashing! §e-§6=§4=\\o>")
            );
        }

    }


    // MP
    @Command(name = "pinchcheeks")
    public void pinchcheeks(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(prefix + "/pinchcheeks <player>");
        } else {
            if (!Cooldown.hasCooldown(player, 10, "§6[§e§lMP§6]§6 You have to wait {secondsLeft} before you can use this command again!")) {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    player.sendMessage(prefix + args[0] + " is not online or not correct.");
                    return;
                }
                Cooldown.giveCooldown(player);

                Bukkit.getOnlinePlayers().forEach(all ->
                        all.sendMessage("§6[§e§lMP§6]§6 " + player.getName() + " §6pinches " + target.getName() + "'s§7 cheeks you've grown up so much!")
                );
            }
        }
    }

    @Command(name = "goteem")
    public void goteem(Player player, String[] args) {
        if (!Cooldown.hasCooldown(player, 10, "§6[§e§lMP§6]§6 You have to wait {secondsLeft} before you can use this command again!")) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(prefix + args[0] + " is not online or not correct.");
                return;
            }
            Cooldown.giveCooldown(player);

            Bukkit.getOnlinePlayers().forEach(all ->
                    all.sendMessage("§6[§e§lMP§6]§6 " + player.getName() + " screams HAAAH §6GOTEEM!")
            );
        }
    }

    @Command(name = "giggle")
    public void giggle(Player player, String[] args) {
        if (!Cooldown.hasCooldown(player, 10, "§6[§e§lMP§6]§6 You have to wait {secondsLeft} before you can use this command again!")) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(prefix + args[0] + " is not online or not correct.");
                return;
            }
            Cooldown.giveCooldown(player);

            Bukkit.getOnlinePlayers().forEach(all ->
                    all.sendMessage("§6[§e§lMP§6]§6 " + player.getName() + " §6giggles§7 huehuehue, how silly :).")
            );
        }
    }


    // BP
    @Command(name = "roar")
    public void roar(Player player, String[] args) {
        if (!Cooldown.hasCooldown(player, 10, "§0[§4BP§0] §cYou have to wait {secondsLeft} before you can use this command again!")) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(prefix + args[0] + " is not online or not correct.");
                return;
            }
            Cooldown.giveCooldown(player);

            Bukkit.getOnlinePlayers().forEach(all ->
                    all.sendMessage("§0[§4BP§0] §c" + player.getName() + " roars ROAR!!!!")
            );
        }
    }


    // AP
    @Command(name = "blowkiss")
    public void blowkiss(Player player, String[] args) {
        if (!Cooldown.hasCooldown(player, 10, "§4[§c§lAP§4] §4You have to wait {secondsLeft} before you can use this command again!")) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(prefix + args[0] + " is not online or not correct.");
                return;
            }
            Cooldown.giveCooldown(player);

            Bukkit.getOnlinePlayers().forEach(all ->
                    all.sendMessage("§4[§c§lAP§4]§4 " + player.getName() + "§7 blows a §4kiss§7 to the wind, whos gunna catch it?")
            );
        }
    }

    @Command(name = "popcorn")
    public void popcorn(Player player, String[] args) {
        if (!Cooldown.hasCooldown(player, 10, "§4[§c§lAP§4] §4You have to wait {secondsLeft} before you can use this command again!")) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(prefix + args[0] + " is not online or not correct.");
                return;
            }
            Cooldown.giveCooldown(player);

            Bukkit.getOnlinePlayers().forEach(all ->
                    all.sendMessage("§4[§c§lAP§4]§4 " + player.getName() + "§7 pops some §4popcorn§7 ooh it's getting real!")
            );
        }
    }

    @Command(name = "burp")
    public void burp(Player player, String[] args) {
        if (!Cooldown.hasCooldown(player, 10, "§4[§c§lAP§4] §4You have to wait {secondsLeft} before you can use this command again!")) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(prefix + args[0] + " is not online or not correct.");
                return;
            }
            Cooldown.giveCooldown(player);

            Bukkit.getOnlinePlayers().forEach(all ->
                    all.sendMessage("§4[§c§lAP§4]§4 " + player.getName() + "§c burped§7 what did they eat recently?")
            );
        }
    }

    @Command(name = "welcome")
    public void welcome(Player player, String[] args) {
        if (!Cooldown.hasCooldown(player, 10, "§4[§c§lAP§4] §4You have to wait {secondsLeft} before you can use this command again!")) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(prefix + args[0] + " is not online or not correct.");
                return;
            }
            Cooldown.giveCooldown(player);

            Bukkit.getOnlinePlayers().forEach(all ->
                    all.sendMessage("§4[§c§lAP§4]§4 " + player.getName() + "§7 welcomes you, do §c./p auto§7 to build!")
            );
        }
    }

    @Command(name = "clap")
    public void clap(Player player, String[] args) {
        if (!Cooldown.hasCooldown(player, 10, "§4[§c§lAP§4] §4You have to wait {secondsLeft} before you can use this command again!")) {
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                player.sendMessage(prefix + args[0] + " is not online or not correct.");
                return;
            }
            Cooldown.giveCooldown(player);

            Bukkit.getOnlinePlayers().forEach(all ->
                    all.sendMessage("§4[§c§lAP§4]§4 " + player.getName() + "§7 claps, good job!")
            );
        }
    }
}
