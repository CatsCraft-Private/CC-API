package api.brainsynder.nms.v1_8_R3;

import api.brainsynder.nms.ItemHandle;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import simple.brainsynder.utils.Valid;

import java.util.HashMap;
import java.util.Map;

public class ItemHandleInstance implements ItemHandle {
    @Override
    public ItemStack downgradeItem(ItemStack item) {
        Valid.notNull(item);
        net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsStack.getTag();
        if (compound == null) {
            compound = new NBTTagCompound();
        }
        if (compound.hasKey("AttributeModifiers"))
            compound.remove("AttributeModifiers");
        nmsStack.setTag(compound);

        ItemStack stack = CraftItemStack.asBukkitCopy(nmsStack);

        Map<Enchantment, Integer> enchantment = new HashMap<>();
        for (Enchantment enchant : stack.getEnchantments().keySet()) {
            int lvl = stack.getEnchantments().get(enchant);
            if (lvl > 6) {
                lvl = 5;
            }
            stack.removeEnchantment(enchant);
            enchantment.put(enchant, lvl);
        }
        for (Enchantment enchant : enchantment.keySet()) {
            int lvl = enchantment.get(enchant);
            stack.addUnsafeEnchantment(enchant, lvl);
        }

        return stack;
    }
}
