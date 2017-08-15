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

import java.util.ArrayList;
import java.util.List;

public class SpinningItem {
    private static List<SpinningItem> items = new ArrayList<>();
    private Location location, sameLocation;
    private ArmorStand armorStand;
    private ItemStack item = null;
    private String name = null;
    private List<ArmorStand> texts = new ArrayList<>();

    public SpinningItem(Location location) {
        this.location = location;
        items.add(this);
    }

    public static void deleteAll() {
        items.forEach(SpinningItem::delete);
        items.clear();
    }

    public static List<SpinningItem> getFloatingItems() {
        return items;
    }

    public void spawn(ItemStack itemStack, boolean big, List<String> text) {
        armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        armorStand.setSmall(!big);
        armorStand.setMetadata("NO_TOUCH", new FixedMetadataValue(Core.get(), "NO_TOUCH"));
        this.item = itemStack;

        this.sameLocation = armorStand.getLocation();


        addText(this, text);
    }

    public void spawn(ItemStack itemStack, boolean big) {
        Core.get().spawnMe = true;
        armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        armorStand.setSmall(big ? false : true);
        armorStand.setMetadata("NO_TOUCH", new FixedMetadataValue(Core.get(), "NO_TOUCH"));
        this.item = itemStack;

        this.sameLocation = armorStand.getLocation();
    }

    public Location update() {
        if (armorStand == null) return null;
        if (armorStand.isDead()) return null;
        if (!armorStand.isValid()) return null;
        Location location = armorStand.getLocation();
        if (item != null)
            armorStand.setHelmet(item);
        if (name != null) {
            armorStand.setCustomNameVisible(true);
            armorStand.setCustomName(ChatColor.translateAlternateColorCodes('&', name));
        }
        location.setYaw((location.getYaw() + 7.5F));
        return location;
    }

    private void addText(SpinningItem floatingItem, List<String> text) {
        ArmorStand armorStand = null;
        List<String> lines = Lists.reverse(text);

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