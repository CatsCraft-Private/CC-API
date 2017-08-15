package api.brainsynder.Utils;

import api.brainsynder.wrappers.ArmorStandWrapper;
import api.brainsynder.wrappers.EulerWrapper;

public class EulerManager {
    private ArmorStandWrapper stand;
    private Axis axis;
    private Movement move;

    public EulerManager(ArmorStandWrapper stand, Axis axis, Movement move) {
        this.stand = stand;
        this.axis = axis;
        this.move = move;
    }


    public void setPose(double angle) {
        if (move == Movement.HEAD) {
            stand.getHead ().set(getAngle(stand.getHead(), angle));
        } else if (move == Movement.LEFT_ARM) {
            stand.getLeftArm ().set(getAngle(stand.getLeftArm(), angle));
        } else if (move == Movement.BODY) {
            stand.getBody ().set(getAngle(stand.getBody(), angle));
        } else if (move == Movement.LEFT_LEG) {
            stand.getLeftLeg ().set (getAngle(stand.getLeftLeg(), angle));
        } else if (move == Movement.RIGHT_ARM) {
            stand.getRightArm ().set(getAngle(stand.getRightArm(), angle));
        } else if (move == Movement.RIGHT_LEG) {
            stand.getRightLeg ().set(getAngle(stand.getRightLeg(), angle));
        }
    }

    private EulerWrapper getAngle(EulerWrapper ea, double angle) {
        switch (axis) {
            case PITCH:
                double PITCH = Math.toRadians(angle);
                PITCH += ea.getPitch();
                return ea.setPitch(PITCH);
            case YAW:
                double YAW = Math.toRadians(angle);
                YAW += ea.getYaw();
                return ea.setYaw(YAW);
            case ROLL:
                double ROLL = Math.toRadians(angle);
                ROLL += ea.getRoll();
                return ea.setRoll(ROLL);
            default:
                return null;
        }
    }
}
