package api.brainsynder.wrappers;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EquipmentWrapper {
    private ItemStack helmet = new ItemStack(Material.AIR);
    private ItemStack chestplate = new ItemStack(Material.AIR);
    private ItemStack leggings = new ItemStack(Material.AIR);
    private ItemStack boots = new ItemStack(Material.AIR);
    private ItemStack mainHand = new ItemStack(Material.AIR);

    public EquipmentWrapper(EntityEquipment equipment) {
        helmet = equipment.getHelmet();
        chestplate = equipment.getChestplate();
        leggings = equipment.getLeggings();
        boots = equipment.getBoots();
        mainHand = equipment.getItemInHand();
    }

    public List<ItemStack> toList () {
        List<ItemStack> items = new ArrayList<>();
        items.add(helmet);
        items.add(chestplate);
        items.add(leggings);
        items.add(boots);
        items.add(mainHand);
        return items;
    }

    public void updateEquipment (ArmorStandWrapper stand) {
        stand.getStand().setHelmet(helmet);
        stand.getStand().setChestplate(chestplate);
        stand.getStand().setLeggings(leggings);
        stand.getStand().setBoots(boots);
        stand.getStand().getEquipment().setItemInHand(mainHand);
    }

    public static void dropItems (ArmorStandWrapper stand) {
        EquipmentWrapper wrapper = stand.getEquipment();
        Location location = stand.getStand().getLocation();
        World world = location.getWorld();
        for (ItemStack item : wrapper.toList()) {
            if (item == null)
                continue;
            if (item.getType() == Material.AIR)
                continue;
            world.dropItem(location, item);
        }
        wrapper.setHelmet(new ItemStack(Material.AIR));
        wrapper.setChestplate(new ItemStack(Material.AIR));
        wrapper.setLeggings(new ItemStack(Material.AIR));
        wrapper.setBoots(new ItemStack(Material.AIR));
        wrapper.setMainHand(new ItemStack(Material.AIR));
        wrapper.updateEquipment(stand);
    }

    public ItemStack getHelmet() {
        return this.helmet;
    }

    public ItemStack getChestplate() {
        return this.chestplate;
    }

    public ItemStack getLeggings() {
        return this.leggings;
    }

    public ItemStack getBoots() {
        return this.boots;
    }

    public ItemStack getMainHand() {
        return this.mainHand;
    }

    public void setHelmet(ItemStack helmet) {
        this.helmet = helmet;
    }

    public void setChestplate(ItemStack chestplate) {
        this.chestplate = chestplate;
    }

    public void setLeggings(ItemStack leggings) {
        this.leggings = leggings;
    }

    public void setBoots(ItemStack boots) {
        this.boots = boots;
    }

    public void setMainHand(ItemStack mainHand) {
        this.mainHand = mainHand;
    }
}
