package api.brainsynder.animation;

import api.brainsynder.wrappers.EulerWrapper;

/**
 * This class was pulled form my SimpleMobs plugin.
 */
public class AnimationManager {

    public static final MovementFrames walk = new MovementFrames(
            (new AnimationFrame())
                    .setLeftLeg(new EulerWrapper(0.5D, 0.0D, 0.0D))
                    .setRightLeg(new EulerWrapper(-0.5D, 0.0D, 0.0D)),
            (new AnimationFrame())
                    .setLeftLeg(new EulerWrapper(0.4D, 0.0D, 0.0D))
                    .setRightLeg(new EulerWrapper(-0.4D, 0.0D, 0.0D)),
            (new AnimationFrame())
                    .setLeftLeg(new EulerWrapper(0.3D, 0.0D, 0.0D))
                    .setRightLeg(new EulerWrapper(-0.3D, 0.0D, 0.0D)),
            (new AnimationFrame())
                    .setLeftLeg(new EulerWrapper(0.2D, 0.0D, 0.0D))
                    .setRightLeg(new EulerWrapper(-0.2D, 0.0D, 0.0D)),
            (new AnimationFrame())
                    .setLeftLeg(new EulerWrapper(0.1D, 0.0D, 0.0D))
                    .setRightLeg(new EulerWrapper(-0.1D, 0.0D, 0.0D)),
            (new AnimationFrame())
                    .setLeftLeg(new EulerWrapper(0.0D, 0.0D, 0.0D))
                    .setRightLeg(new EulerWrapper(0.0D, 0.0D, 0.0D)),
            (new AnimationFrame())
                    .setLeftLeg(new EulerWrapper(-0.1D, 0.0D, 0.0D))
                    .setRightLeg(new EulerWrapper(0.1D, 0.0D, 0.0D)),
            (new AnimationFrame())
                    .setLeftLeg(new EulerWrapper(-0.2D, 0.0D, 0.0D))
                    .setRightLeg(new EulerWrapper(0.2D, 0.0D, 0.0D)),
            (new AnimationFrame())
                    .setLeftLeg(new EulerWrapper(-0.3D, 0.0D, 0.0D))
                    .setRightLeg(new EulerWrapper(0.3D, 0.0D, 0.0D)),
            (new AnimationFrame())
                    .setLeftLeg(new EulerWrapper(-0.4D, 0.0D, 0.0D))
                    .setRightLeg(new EulerWrapper(0.4D, 0.0D, 0.0D)),
            (new AnimationFrame())
                    .setLeftLeg(new EulerWrapper(-0.5D, 0.0D, 0.0D))
                    .setRightLeg(new EulerWrapper(0.5D, 0.0D, 0.0D)),
            (new AnimationFrame())
                    .setLeftLeg(new EulerWrapper(-0.4D, 0.0D, 0.0D))
                    .setRightLeg(new EulerWrapper(0.4D, 0.0D, 0.0D)),
            (new AnimationFrame())
                    .setLeftLeg(new EulerWrapper(-0.3D, 0.0D, 0.0D))
                    .setRightLeg(new EulerWrapper(0.3D, 0.0D, 0.0D)),
            (new AnimationFrame())
                    .setLeftLeg(new EulerWrapper(-0.2D, 0.0D, 0.0D))
                    .setRightLeg(new EulerWrapper(0.2D, 0.0D, 0.0D)),
            (new AnimationFrame())
                    .setLeftLeg(new EulerWrapper(-0.1D, 0.0D, 0.0D))
                    .setRightLeg(new EulerWrapper(0.1D, 0.0D, 0.0D)),
            (new AnimationFrame())
                    .setLeftLeg(new EulerWrapper(0.0D, 0.0D, 0.0D))
                    .setRightLeg(new EulerWrapper(0.0D, 0.0D, 0.0D)),
            (new AnimationFrame())
                    .setLeftLeg(new EulerWrapper(0.1D, 0.0D, 0.0D))
                    .setRightLeg(new EulerWrapper(-0.1D, 0.0D, 0.0D)),
            (new AnimationFrame())
                    .setLeftLeg(new EulerWrapper(0.2D, 0.0D, 0.0D))
                    .setRightLeg(new EulerWrapper(-0.2D, 0.0D, 0.0D)),
            (new AnimationFrame())
                    .setLeftLeg(new EulerWrapper(0.3D, 0.0D, 0.0D))
                    .setRightLeg(new EulerWrapper(-0.3D, 0.0D, 0.0D)),
            (new AnimationFrame())
                    .setLeftLeg(new EulerWrapper(0.4D, 0.0D, 0.0D))
                    .setRightLeg(new EulerWrapper(-0.4D, 0.0D, 0.0D)));
    public static final MovementFrames arm_swing = new MovementFrames(
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(0.5, 0, 0))
                    .setRightHand(new EulerWrapper(-0.5, 0, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(0.4, 0, 0))
                    .setRightHand(new EulerWrapper(-0.4, 0, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(0.3, 0, 0))
                    .setRightHand(new EulerWrapper(-0.3, 0, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(0.2, 0, 0))
                    .setRightHand(new EulerWrapper(-0.2, 0, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(0.1, 0, 0))
                    .setRightHand(new EulerWrapper(-0.1, 0, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(0, 0, 0))
                    .setRightHand(new EulerWrapper(0, 0, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(-0.1, 0, 0))
                    .setRightHand(new EulerWrapper(0.1, 0, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(-0.2, 0, 0))
                    .setRightHand(new EulerWrapper(0.2, 0, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(-0.3, 0, 0))
                    .setRightHand(new EulerWrapper(0.3, 0, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(-0.4, 0, 0))
                    .setRightHand(new EulerWrapper(0.4, 0, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(-0.5, 0, 0))
                    .setRightHand(new EulerWrapper(0.5, 0, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(-0.4, 0, 0))
                    .setRightHand(new EulerWrapper(0.4, 0, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(-0.3, 0, 0))
                    .setRightHand(new EulerWrapper(0.3, 0, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(-0.2, 0, 0))
                    .setRightHand(new EulerWrapper(0.2, 0, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(-0.1, 0, 0))
                    .setRightHand(new EulerWrapper(0.1, 0, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(0, 0, 0))
                    .setRightHand(new EulerWrapper(0, 0, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(0.1, 0, 0))
                    .setRightHand(new EulerWrapper(-0.1, 0, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(0.2, 0, 0))
                    .setRightHand(new EulerWrapper(-0.2, 0, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(0.3, 0, 0))
                    .setRightHand(new EulerWrapper(-0.3, 0, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(0.4, 0, 0))
                    .setRightHand(new EulerWrapper(-0.4, 0, 0))
    );
    public static final MovementFrames dab = new MovementFrames(
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(0, 0, 0))
                    .setRightHand(new EulerWrapper(0, 0, 0))
                    .setHead(new EulerWrapper(0, 0, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(0, 0, -43))
                    .setRightHand(new EulerWrapper(-31, 0, 0))
                    .setHead(new EulerWrapper(44, -316, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(0, 0, -23))
                    .setRightHand(new EulerWrapper(-11, 0, 0))
                    .setHead(new EulerWrapper(44, -316, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(0, 0, -143))
                    .setRightHand(new EulerWrapper(-131, 0, 143))
                    .setHead(new EulerWrapper(44, -316, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(0, 0, 243))
                    .setRightHand(new EulerWrapper(331, 0, 243))
                    .setHead(new EulerWrapper(44, -316, 0)),
            (new AnimationFrame())
                    .setLeftHand(new EulerWrapper(0, 0, 0))
                    .setRightHand(new EulerWrapper(0, 0, 0))
    );

    private static double val = 0.05;
    private static double val2 = 0.02;
    public static final MovementFrames floating = new MovementFrames(
            (new AnimationFrame().addY(val)),
            (new AnimationFrame().addY(val)),
            (new AnimationFrame().addY(val)),
            (new AnimationFrame().addY(val)),
            (new AnimationFrame().addY(val)),
            (new AnimationFrame().addY(val)),
            (new AnimationFrame().addY(val)),
            (new AnimationFrame().addY(val)),
            (new AnimationFrame().addY(val)),
            (new AnimationFrame().addY(val)),
            (new AnimationFrame().addY(val)),
            (new AnimationFrame().addY(val)),
            (new AnimationFrame().addY(val)),
            (new AnimationFrame().addY(val2)),
            (new AnimationFrame().addY(val2)),

            (new AnimationFrame().addY(-val2)),
            (new AnimationFrame().addY(-val2)),
            (new AnimationFrame().addY(-val)),
            (new AnimationFrame().addY(-val)),
            (new AnimationFrame().addY(-val)),
            (new AnimationFrame().addY(-val)),
            (new AnimationFrame().addY(-val)),
            (new AnimationFrame().addY(-val)),
            (new AnimationFrame().addY(-val)),
            (new AnimationFrame().addY(-val)),
            (new AnimationFrame().addY(-val)),
            (new AnimationFrame().addY(-val)),
            (new AnimationFrame().addY(-val)),
            (new AnimationFrame().addY(-val)),
            (new AnimationFrame().addY(-val))
    );
}
