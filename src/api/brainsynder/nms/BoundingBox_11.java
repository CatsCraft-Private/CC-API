package api.brainsynder.nms;

/*import net.minecraft.server.v1_11_R1.AxisAlignedBB;
import net.minecraft.server.v1_11_R1.BlockPosition;
import net.minecraft.server.v1_11_R1.WorldServer;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_11_R1.CraftWorld;
import org.bukkit.util.Vector;*/

@Deprecated
public class BoundingBox_11 {
    /*
    //min and max points of hit box
    public Vector max;
    public Vector min;

    public BoundingBox_11(Vector min, Vector max) {
        this.max = max;
        this.min = min;
    }

    public BoundingBox_11(Block block) {
        BlockPosition pos = new BlockPosition(block.getX(), block.getY(), block.getZ());
        WorldServer world = ((CraftWorld) block.getWorld()).getHandle();
        AxisAlignedBB box = world.getType(pos).c(world, pos);
        min = new Vector(pos.getX() + box.a, pos.getY() + box.b, pos.getZ() + box.c);
        max = new Vector(pos.getX() + box.d, pos.getY() + box.e, pos.getZ() + box.f);
    }

    public BoundingBox_11(AxisAlignedBB bb) {
        min = new Vector(bb.a, bb.b, bb.c);
        max = new Vector(bb.d, bb.e, bb.f);
    }
    
    public Vector midPoint() {
        return max.clone().add(min).multiply(0.5);
    }

    public Vector getMax() {
        return this.max;
    }

    public Vector getMin() {
        return this.min;
    }*/
}