package api.brainsynder.Utils;

import api.brainsynder.Core;
import com.google.common.collect.Lists;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FloatingItem {
    private static List<FloatingItem> items = new ArrayList<>();
    private Location location, sameLocation;
    private ArmorStand armorStand;
    private ItemStack item;
    private String name = null;
    private boolean floatLoop;
    private List<ArmorStand> texts = new ArrayList<>();

    public FloatingItem(Location location) {
        this.location = location;
        this.floatLoop = true;

        items.add(this);
    }

    public static void enable(JavaPlugin plugin) {
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    if (!FloatingItem.getFloatingItems().isEmpty())
                        FloatingItem.getFloatingItems().stream()
                                .filter(i -> i.getArmorStand() != null)
                                .forEach(FloatingItem::update);
                } catch (Exception e) {
                }
            }
        }.runTaskTimerAsynchronously(plugin, 0, 1);
    }

    public static void deleteAll() {
        items.forEach(FloatingItem::delete);
        items.clear();
    }

    public static List<FloatingItem> getFloatingItems() {
        return items;
    }

    public void spawn(ItemStack itemStack, boolean big, String[] text) {
        armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setGravity(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setVisible(false);
        armorStand.setSmall(big ? false : true);
        armorStand.setMetadata("NO_TOUCH", new FixedMetadataValue(Core.get(), "NO_TOUCH"));
        this.item = itemStack;

        this.sameLocation = armorStand.getLocation();


        addText(this, text);
    }

    public void spawn(ItemStack itemStack, boolean big) {
        Core.get().spawnMe = true;
        armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setGravity(false);
        armorStand.setCustomNameVisible(true);
        armorStand.setVisible(false);
        armorStand.setSmall(big ? false : true);
        armorStand.setMetadata("NO_TOUCH", new FixedMetadataValue(Core.get(), "NO_TOUCH"));
        this.item = itemStack;

        this.sameLocation = armorStand.getLocation();
    }

    public void update() {
        if (armorStand == null) return;
        if (armorStand.isDead()) return;
        if (!armorStand.isValid()) return;
        Location location = armorStand.getLocation();
        armorStand.setHelmet(item);
        if (name != null)
            armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
        if (!this.floatLoop) {
            location.add(0, 0.01, 0);
            location.setYaw((location.getYaw() + 7.5F));

            armorStand.teleport(location);

            if (armorStand.getLocation().getY() > (0.25 + sameLocation.getY()))
                this.floatLoop = true;
        } else {
            location.subtract(0, 0.01, 0);
            location.setYaw((location.getYaw() - 7.5F));

            armorStand.teleport(location);

            if (armorStand.getLocation().getY() < (-0.25 + sameLocation.getY()))
                this.floatLoop = false;
        }
    }

    private void addText(FloatingItem floatingItem, String... text) {
        ArmorStand armorStand = null;
        List<String> lines = Arrays.asList(text);
        lines = Lists.reverse(lines);

        double y = 0.25D;

        for (String line : lines) {
            armorStand = (ArmorStand) location.getWorld().spawnEntity(location.clone().add(0, y, 0), EntityType.ARMOR_STAND);
            armorStand.setGravity(false);
            armorStand.setCustomName(line.replace('&', '§'));
            armorStand.setCustomNameVisible(true);
            armorStand.setVisible(false);
            y += 0.21D;

            texts.add(armorStand);
        }
    }

    public void deleteAllText() {
        texts.forEach(Entity::remove);
    }

    public void delete() {
        if (!texts.isEmpty())
            deleteAllText();
        if (armorStand != null)
            armorStand.remove();
        armorStand = null;
        reset();
    }

    public void reset() {
        items.remove(this);
    }

    public List<ArmorStand> getTexts() {
        return texts;
    }

    public Location getLocation() {
        return location;
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public void setName(String name) {
        this.name = name;
    }
}