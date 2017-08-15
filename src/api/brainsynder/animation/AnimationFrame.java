package api.brainsynder.animation;

import api.brainsynder.wrappers.ArmorStandWrapper;
import api.brainsynder.wrappers.EulerWrapper;

public class AnimationFrame {
    private EulerWrapper head = null;
    private EulerWrapper body = null;
    private EulerWrapper leftHand = null;
    private EulerWrapper rightHand = null;
    private EulerWrapper leftLeg = null;
    private EulerWrapper rightLeg = null;
    private double x = 0, y = 0, z = 0;

    public AnimationFrame addX(double x) {
        this.x = x;
        return this;
    }

    public AnimationFrame addY(double y) {
        this.y = y;
        return this;
    }

    public AnimationFrame addZ(double z) {
        this.z = z;
        return this;
    }

    public AnimationFrame setHead(EulerWrapper head) {
        this.head = head;
        return this;
    }

    public AnimationFrame setBody(EulerWrapper body) {
        this.body = body;
        return this;
    }

    public AnimationFrame setLeftHand(EulerWrapper leftHand) {
        this.leftHand = leftHand;
        return this;
    }

    public AnimationFrame setRightHand(EulerWrapper rightHand) {
        this.rightHand = rightHand;
        return this;
    }

    public AnimationFrame setLeftLeg(EulerWrapper leftLeg) {
        this.leftLeg = leftLeg;
        return this;
    }

    public AnimationFrame setRightLeg(EulerWrapper rightLeg) {
        this.rightLeg = rightLeg;
        return this;
    }

    public void setLocations(ArmorStandWrapper armor) {
        if (this.head != null)
            armor.getHead().set(this.head);
        if (this.body != null)
            armor.getBody().set(this.body);
        if (this.leftHand != null)
            armor.getLeftArm().set(this.leftHand);
        if (this.rightHand != null)
            armor.getRightArm().set(this.rightHand);
        if (this.leftLeg != null)
            armor.getLeftLeg().set(this.leftLeg);
        if (this.rightLeg != null)
            armor.getRightLeg().set(this.rightLeg);
        armor.getStand().teleport(armor.getStand().getLocation().add(x, y, z));
    }

    public EulerWrapper getHead() {
        return this.head;
    }

    public EulerWrapper getBody() {
        return this.body;
    }

    public EulerWrapper getLeftHand() {
        return this.leftHand;
    }

    public EulerWrapper getRightHand() {
        return this.rightHand;
    }

    public EulerWrapper getLeftLeg() {
        return this.leftLeg;
    }

    public EulerWrapper getRightLeg() {
        return this.rightLeg;
    }
}
