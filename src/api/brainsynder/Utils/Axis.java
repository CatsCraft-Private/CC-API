package api.brainsynder.Utils;

public enum Axis {
    YAW,
    PITCH,
    ROLL;

    public static Axis fromString(String name) {
                return valueOf(name.toUpperCase());
    }
}
