package api.brainsynder.wrappers;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.util.EulerAngle;
import simple.brainsynder.nbt.CompressedStreamTools;
import simple.brainsynder.nbt.StorageTagCompound;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.UUID;

@SuppressWarnings("ALL")
public class ArmorStandWrapper {
    private ArmorStand stand;
    private ArrayList<String> trustedPlayers;
    private String ownerUUID;
    private EulerWrapper leftArm;
    private EulerWrapper rightArm;
    private EulerWrapper leftLeg;
    private EulerWrapper rightLeg;
    private EulerWrapper head;
    private EulerWrapper body;
    private EquipmentWrapper equipment;

    public ArmorStandWrapper(ArmorStand stand) {
        this.stand = stand;
        this.body = new EulerWrapper(stand.getBodyPose());
        this.head = new EulerWrapper(stand.getHeadPose());
        this.rightLeg = new EulerWrapper(stand.getRightLegPose());
        this.leftLeg = new EulerWrapper(stand.getLeftLegPose());
        this.rightArm = new EulerWrapper(stand.getRightArmPose());
        this.leftArm = new EulerWrapper(stand.getLeftArmPose());
        equipment = new EquipmentWrapper(stand.getEquipment());
    }

    public static ArmorStandWrapper fromUUID(UUID uuid) {
        for (World world : Bukkit.getWorlds()) {
            for (Chunk chunk : world.getLoadedChunks()) {
                for (Entity entity : chunk.getEntities()) {
                    if (entity.getUniqueId().equals(uuid)) {
                        if (entity.isDead()) {
                            return null;
                        } else {
                            if (entity.getLocation().getY() <= 0.0) {
                                entity.remove();
                                return null;
                            }
                        }
                        return new ArmorStandWrapper((ArmorStand) entity);
                    }
                }
            }
        }
        return null;
    }

    public UUID getUUID() {
        return stand.getUniqueId();
    }

    public boolean isDead() {
        if (stand.isDead()) {
            return true;
        } else {
            if (stand.getLocation().getY() <= 0.0) {
                stand.remove();
                return true;
            }
        }
        return false;
    }

    public void update() {
        stand.setLeftArmPose(leftArm.toEulerAngle());
        stand.setLeftLegPose(leftLeg.toEulerAngle());
        stand.setRightArmPose(rightArm.toEulerAngle());
        stand.setRightLegPose(rightLeg.toEulerAngle());
        stand.setBodyPose(body.toEulerAngle());
        stand.setHeadPose(head.toEulerAngle());
    }

    public void reset() {
        head.set(new EulerWrapper(new EulerAngle(0.0, 0.0, 0.0)));
        body.set(new EulerWrapper(new EulerAngle(0.0, 0.0, 0.0)));
        leftArm.set(new EulerWrapper(new EulerAngle(0.0, 0.0, 0.0)));
        leftLeg.set(new EulerWrapper(new EulerAngle(0.0, 0.0, 0.0)));
        rightArm.set(new EulerWrapper(new EulerAngle(0.0, 0.0, 0.0)));
        rightLeg.set(new EulerWrapper(new EulerAngle(0.0, 0.0, 0.0)));
        update();

        stand.setSmall(false);
        stand.setArms(false);
        stand.setVisible(true);
        stand.setBasePlate(true);
        stand.setCustomNameVisible(false);
        stand.setGravity(true);
        stand.setCustomName("Armor Stand");
    }

    public StorageTagCompound toCompound() {
        StorageTagCompound object = new StorageTagCompound();
        object.setTag("leftArm", leftArm.toCompound());
        object.setTag("leftLeg", leftLeg.toCompound());
        object.setTag("rightArm", rightArm.toCompound());
        object.setTag("rightLeg", rightLeg.toCompound());
        object.setTag("body", body.toCompound());
        object.setTag("head", head.toCompound());
        object.setBoolean("small", stand.isSmall());
        object.setBoolean("visible", stand.isVisible());
        object.setBoolean("baseplate", stand.hasBasePlate());
        object.setBoolean("arms", stand.hasArms());
        object.setBoolean("nameVisible", stand.isCustomNameVisible());
        if (stand.getCustomName() != null)
            object.setString("CustomName", stand.getCustomName());
        object.setBoolean("gravity", stand.hasGravity());
        return object;
    }

    public void saveData(File folder, String fileName) {
        try {
            if (!folder.exists()) folder.mkdirs();
            File file = new File(folder, fileName + ".nbt");
            if (!file.exists()) file.createNewFile();
            CompressedStreamTools.writeCompressed(toCompound(), new FileOutputStream(file));
            System.out.println(toCompound().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(File folder, String fileName) {
        if (!folder.exists()) {
            folder.mkdirs();
            return;
        }

        try {
            File file = new File(folder, fileName + ".nbt");
            if (!file.exists()) return;
            FileInputStream stream = new FileInputStream(file);
            StorageTagCompound object = CompressedStreamTools.readCompressed(stream);
            if (object.hasKey("small")) stand.setSmall(object.getBoolean("small"));
            if (object.hasKey("arms")) stand.setArms(object.getBoolean("arms"));
            if (object.hasKey("visible")) stand.setVisible(object.getBoolean("visible"));
            if (object.hasKey("baseplate")) stand.setBasePlate(object.getBoolean("baseplate"));
            if (object.hasKey("nameVisible")) stand.setCustomNameVisible(object.getBoolean("nameVisible"));
            if (object.hasKey("gravity")) stand.setGravity(object.getBoolean("gravity"));
            if (object.hasKey("CustomName")) stand.setCustomName(String.valueOf(object.getString("CustomName")));
            if (object.hasKey("body")) this.body = new EulerWrapper(object.getCompoundTag("body"));
            if (object.hasKey("head")) this.head = new EulerWrapper(object.getCompoundTag("head"));
            if (object.hasKey("rightLeg")) this.rightLeg = new EulerWrapper(object.getCompoundTag("rightLeg"));
            if (object.hasKey("leftLeg")) this.leftLeg = new EulerWrapper(object.getCompoundTag("leftLeg"));
            if (object.hasKey("rightArm")) this.rightArm = new EulerWrapper(object.getCompoundTag("rightArm"));
            if (object.hasKey("leftArm")) this.leftArm = new EulerWrapper(object.getCompoundTag("leftArm"));
            update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void teleport(double x, double y, double z) {
        Location location = stand.getLocation().clone();
        location.add(x, y, z);
        stand.teleport(location);
    }

    public ArmorStand getStand() {
        return this.stand;
    }

    public ArrayList<String> getTrustedPlayers() {
        return this.trustedPlayers;
    }

    public String getOwnerUUID() {
        return this.ownerUUID;
    }

    public EulerWrapper getLeftArm() {
        return this.leftArm;
    }

    public EulerWrapper getRightArm() {
        return this.rightArm;
    }

    public EulerWrapper getLeftLeg() {
        return this.leftLeg;
    }

    public EulerWrapper getRightLeg() {
        return this.rightLeg;
    }

    public EulerWrapper getHead() {
        return this.head;
    }

    public EulerWrapper getBody() {
        return this.body;
    }

    public EquipmentWrapper getEquipment() {
        return this.equipment;
    }
}
