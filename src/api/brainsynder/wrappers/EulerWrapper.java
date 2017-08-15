package api.brainsynder.wrappers;

import org.bukkit.util.EulerAngle;
import simple.brainsynder.nbt.StorageTagCompound;

public class EulerWrapper {
    private double yaw;
    private double pitch;
    private double roll;

    public EulerWrapper(EulerAngle angle) {
        this.pitch = angle.getY();
        this.yaw = angle.getX();
        this.roll = angle.getZ();
    }

    public EulerWrapper(double pitch, double yaw, double roll) {
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    public EulerWrapper(StorageTagCompound object) {
        if (object.hasKey("pitch")) this.pitch = object.getDouble("pitch");
        if (object.hasKey("yaw")) this.yaw = object.getDouble("yaw");
        if (object.hasKey("roll")) this.roll = object.getDouble("roll");
    }

    public EulerWrapper setPitch(double pitch) {
        this.pitch = pitch;
        return this;
    }

    public EulerWrapper setRoll(double roll) {
        this.roll = roll;
        return this;
    }

    public EulerWrapper setYaw(double yaw) {
        this.yaw = yaw;
        return this;
    }

    public void set(EulerWrapper angle) {
        setPitch(angle.pitch);
        setRoll(angle.roll);
        setYaw(angle.yaw);
    }

    public StorageTagCompound toCompound() {
        StorageTagCompound object = new StorageTagCompound();
        object.setDouble("pitch", pitch);
        object.setDouble("yaw", yaw);
        object.setDouble("roll", roll);
        return object;
    }

    public EulerAngle toEulerAngle() {
        return new EulerAngle(this.yaw, this.pitch, this.roll);
    }

    public double getYaw() {
        return this.yaw;
    }

    public double getPitch() {
        return this.pitch;
    }

    public double getRoll() {
        return this.roll;
    }
}
