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

    private static double val = 0.01;
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
