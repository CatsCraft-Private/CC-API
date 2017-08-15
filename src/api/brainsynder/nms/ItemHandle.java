package api.brainsynder.nms;

import org.bukkit.inventory.ItemStack;

import static simple.brainsynder.utils.Reflection.getVersion;

public interface ItemHandle {
    default ItemStack downgradeItem(ItemStack item) {
        return item;
    }

    class ClassGetter {
        public static ItemHandle getInstance () {
            try {
                Class<?> handle = Class.forName("api.brainsynder.nms." + getVersion() + ".ItemHandleInstance");
                if (ItemHandle.class.isAssignableFrom(handle))
                    return (ItemHandle)handle.newInstance();
            }catch (Exception ignored){ }
            throw new InternalError("Could not find a class to handle the ItemHandle.class for version: " + getVersion());
        }
    }
}
