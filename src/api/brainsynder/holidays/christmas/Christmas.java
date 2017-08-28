package api.brainsynder.holidays.christmas;

import api.brainsynder.Core;
import api.brainsynder.Utils.BlockLocation;
import api.brainsynder.commands.api.Command;
import api.brainsynder.holidays.HolidayEvents;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import simple.brainsynder.api.ParticleMaker;

import java.util.Set;

public class Christmas extends HolidayEvents {
    private BlockLocation location = null;

    public Christmas() {
        super("12");
    }

    @Override
    public void load() {
        super.load();
        if (fileExists(locDir, "ChristmasChest")) {
            location = BlockLocation.fromFile(locDir, "ChristmasChest");
        }
    }

    @Command(name = "setChristmasChest")
    public void run(Player p) {
        Block block = p.getTargetBlock(((Set<Material>)null), 10);
        if (block == null) return;
        if (block.getType() == Material.AIR) return;
        if (block.getType() != Material.CHEST) return;
        BlockLocation blockLocation = new BlockLocation(block.getLocation());

        if (location == null) {
            location = blockLocation;
            location.save(locDir, "ChristmasChest");
            p.sendMessage("Set Chest");
            return;
        }

        if (!blockLocation.atLocation(location)) {
            location = blockLocation;
            location.save(locDir, "ChristmasChest");
            p.sendMessage("Set Chest");
        }
    }

    @EventHandler
    public void onClick (PlayerInteractEvent e) {
        Block block = e.getClickedBlock();
        if (block == null) return;
        if (block.getType() == Material.AIR) return;
        if (block.getType() != Material.CHEST) return;
        if (location == null) return;
        BlockLocation blockLocation = new BlockLocation(block.getLocation());
        if (blockLocation.atLocation(location)) {
            e.setCancelled(true);
            e.setUseInteractedBlock(Event.Result.DENY);
            e.setUseItemInHand(Event.Result.DENY);
            OpenChestParticleTask task = new OpenChestParticleTask (e.getPlayer(), e.getPlayer().getItemInHand(), location.toLocation());
            task.runTaskTimer(Core.get(), 0, 1);
        }
    }

    public static class OpenChestParticleTask extends BukkitRunnable {
        private ItemStack item = null;
        private Location loc = null;
        private Player player = null;
        private double t = 0;
        private ArmorStand stand = null;

        public OpenChestParticleTask(Player player, ItemStack item, Location loc) {
            this.loc = loc;
            this.item = item;
            this.player = player;
        }

        @Override public void run() {
            if (loc == null) {
                cancel();
                return;
            }
            double height = 12;
            if (t > height) {
                stand.setGravity(true);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (stand == null) {
                            cancel();
                            return;
                        }
                        if (!stand.isValid()) {
                            cancel();
                            return;
                        }
                        if (stand.isOnGround() || (stand.getLocation().getBlock().getRelative(BlockFace.DOWN) != null)) {
                            Location l = stand.getLocation();
                            l.setYaw(-player.getLocation().getYaw());
                            stand.teleport(l);
                            final Firework firework = (Firework) loc.getWorld().spawnEntity(stand.getLocation(),
                                    EntityType.FIREWORK);
                            FireworkMeta meta = firework.getFireworkMeta();
                            meta.addEffect(
                                    FireworkEffect.builder().withColor(Color.RED, Color.ORANGE, Color.YELLOW).with(FireworkEffect.Type.BALL).build());
                            firework.setFireworkMeta(meta);
                            new BukkitRunnable() {
                                @Override public void run() {
                                    firework.detonate();
                                }
                            }.runTaskLater(Core.get(), 1L);
                            cancel();
                        }
                    }
                }.runTaskTimer(Core.get(), 0,1);
                cancel();
                return;
            }
            double y = t / 4;
            double x = Math.sin(t);
            double z = Math.cos(t);

            double locY = (loc.getY() + y + 1);

            Location location = new Location(loc.getWorld(), (loc.getX() + x + 0.5), locY, (loc.getZ() + z + 0.5));
            Location standLoc = new Location(loc.getWorld(), (loc.getX() + 0.5), locY, (loc.getZ() + 0.5));

            if (stand == null) {
                stand = loc.getWorld().spawn(standLoc, ArmorStand.class);
                stand.setGravity(false);
                stand.setBasePlate(false);
                stand.setVisible(false);
                stand.setSmall(true);
                stand.setHelmet(item);
            }
            standLoc.setYaw((stand.getLocation().getYaw() + 7.5F));
            stand.teleport(standLoc);

            ParticleMaker maker = new ParticleMaker(ParticleMaker.Particle.FIREWORKS_SPARK, 1, 0);
            maker.sendToLocation(location.clone().add(0, 0.9, 0));
            t += 0.1;
        }
    }
}
