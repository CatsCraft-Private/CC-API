package api.brainsynder.Utils;

public enum Movement {
    TELEPORT ("Teleport"),
    LEFT_ARM ("Left-Arm"),
    RIGHT_ARM ("Right-Arm"),
    LEFT_LEG ("Left-Leg"),
    RIGHT_LEG ("Right-Leg"),
    BODY ("Body"),
    HEAD ("Head");

    private String s;
    private Movement (String s) {
        this.s = s;
    }

    public String getName () {
        return s;
    }

    public static Movement fromString (String s) {
        for (Movement move : values()) {
            if (move.s.equals(s)) {
                return move;
            }
        }
        return null;
    }
}
