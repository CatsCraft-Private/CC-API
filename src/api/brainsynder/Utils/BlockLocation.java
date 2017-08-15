package api.brainsynder.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import simple.brainsynder.nbt.CompressedStreamTools;
import simple.brainsynder.nbt.StorageTagCompound;
import simple.brainsynder.utils.Valid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@SuppressWarnings("ALL") // Get rid of those pesky little warnings xD
public class BlockLocation {
    private World world;
    private int x,y,z;
    private StorageTagCompound compound;

    /**
     * Class Getters
     */
    public BlockLocation(World world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;

        StorageTagCompound compound = new StorageTagCompound();
        compound.setString("world", world.getName());
        compound.setInteger("x", x);
        compound.setInteger("y", y);
        compound.setInteger("z", z);
        this.compound = compound;
    }
    public BlockLocation(StorageTagCompound compound) {
        this.world = Bukkit.getWorld(compound.getString("world"));
        this.x = compound.getInteger("x");
        this.y = compound.getInteger("y");
        this.z = compound.getInteger("z");
        this.compound = compound;
    }
    public BlockLocation(Location location) {
        this(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
    public static BlockLocation fromString (String str) {
        str = str.replace("BlockLocation:[world=", "");
        str = str.replace("x=", "");
        str = str.replace("y=", "");
        str = str.replace("z=", "");
        str = str.replace("]", "");
        String[] args = str.split(",");
        try {
            World world = Bukkit.getWorld(args[0]);
            int x = Integer.parseInt(args[1]);
            int y = Integer.parseInt(args[2]);
            int z = Integer.parseInt(args[3]);
            return new BlockLocation(world, x, y, z);
        }catch (Exception e){
            return null;
        }
    }
    public static BlockLocation fromFile (File folder, String fileName) {
        if (!folder.exists()) {
            folder.mkdirs();
            return null;
        }

        try {
            File file = new File(folder, fileName + ".nbt");
            if (!file.exists()) return null;
            FileInputStream stream = new FileInputStream(file);
            return new BlockLocation(CompressedStreamTools.readCompressed(stream));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Checks if this location contains the EXACT coordinates of another BlockLocation
     */
    public boolean atLocation (BlockLocation location) {
        Valid.notNull(location);
        return ((location.world.getName().equals(world.getName()))
                && (location.x == x)
                && (location.y == y)
                && (location.z == z)
        );
    }

    /**
     * Getter Methods
     */
    public World getWorld() {
        return this.world;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getZ() {
        return this.z;
    }
    public StorageTagCompound getCompound () {
        return compound;
    }

    /**
     * Setter Methods
     */
    public void setWorld(World world) {
        this.world = world;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setZ(int z) {
        this.z = z;
    }

    /**
     * Output Data
     */
    public String toDataString() {
        return "BlockLocation:[world=" + world.getName() + ",x=" + x + ",y=" + y + ",z=" + z + ']';
    }
    public void save (File folder, String fileName) {
        try {
            if (!folder.exists()) folder.mkdirs();
            File file = new File(folder, fileName + ".nbt");
            if (!file.exists()) file.createNewFile();
            CompressedStreamTools.writeCompressed(compound, new FileOutputStream(file));
            System.out.println(compound.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Location toLocation () {
        return new Location(world, x, y, z);
    }
}
