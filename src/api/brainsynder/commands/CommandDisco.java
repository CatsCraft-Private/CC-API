package api.brainsynder.commands;

import api.brainsynder.Core;
import api.brainsynder.Utils.Cooldown;
import api.brainsynder.Utils.FloatingItem;
import api.brainsynder.commands.api.Command;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import simple.brainsynder.api.ItemMaker;
import simple.brainsynder.api.ParticleMaker;
import simple.brainsynder.wrappers.MaterialWrapper;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CommandDisco extends CommandCore {
    private List<Byte> datas = Arrays.asList(
            (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5,
            (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10,
            (byte) 11, (byte) 12, (byte) 13, (byte) 14
    );
    private List<ParticleMaker> particles = Arrays.asList(
            new ParticleMaker(ParticleMaker.Particle.REDSTONE, 8, 0.6),
            new ParticleMaker(ParticleMaker.Particle.CRIT, 8, 1.0),
            new ParticleMaker(ParticleMaker.Particle.WATER_WAKE, 8, 0.6),
            new ParticleMaker(ParticleMaker.Particle.SNOWBALL, 8, 0.598)
    );

    @Command(name = "disco")
    public void run(Player p) {
        if (Core.get().isBlockCommands()) {
            p.sendMessage(prefix + "Sorry, Chat Commands are disabled until further notice.");
            return;
        }

        final int[] i = {0};
        if (!Cooldown.hasCooldown(p, 20)) {
            Cooldown.giveCooldown(p);
            Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(prefix + p.getName() + " is having a Disco party!"));
            FloatingItem item = new FloatingItem(p.getLocation());
            item.spawn(new ItemMaker(MaterialWrapper.STAINED_GLASS, datas.get(new Random().nextInt(datas.size()))).create(), true);
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (i[0] == 1) {
                        cancel();
                        return;
                    }
                    Random random = new Random();
                    for (ParticleMaker maker : particles) {
                        maker.sendToLocation(item.getArmorStand().getEyeLocation());
                    }
                    RandomRainbowLetters letters = new RandomRainbowLetters("DISCO");
                    item.setName(letters.getText());
                    item.setItem(new ItemMaker(MaterialWrapper.STAINED_GLASS, datas.get(new Random().nextInt(datas.size()))).create());
                }
            }.runTaskTimerAsynchronously(Core.get(), 0, 5);
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (i[0] == 0) {
                        item.delete();
                        i[0] = 1;
                    }
                }
            }.runTaskLater(Core.get(), 20 * 10);
        }
    }
}
