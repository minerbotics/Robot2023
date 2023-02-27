// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    /**
     * The left-to-right distance between the drivetrain wheels
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = .4572; 
    /**
     * The front-to-back distance between the drivetrain wheels.
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_WHEELBASE_METERS = .600075; 

    public static final int DRIVETRAIN_PIGEON_ID = 0; // FIXME Set Pigeon ID

    public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 5; 
    public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 6; 
    public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 9;
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(50.80078529168253);

    public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 1; 
    public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 2; 
    public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 11; 
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(128.49608930826142); 

    public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 7; 
    public static final int BACK_LEFT_MODULE_STEER_MOTOR = 8; 
    public static final int BACK_LEFT_MODULE_STEER_ENCODER = 10;
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(257.255851239242);

    public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 3;
    public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 4;
    public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 12;
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(11.513672082005296);

    public static final int PCM_ID = 0;

    public final static class GrabberConstants {
        public static final int FORWARD_CHANNEL = 2;
        public static final int REVERSE_CHANNEL = 1;
    }
    public final static class SliderConstants {
        public static final int FORWARD_CHANNEL = 0;
        public static final int REVERSE_CHANNEL = 3;
    }
    public final static class LifterConstants {
        public static final int FORWARD_CHANNEL = 5;
        public static final int REVERSE_CHANNEL = 4;
    }
    public final static class ArmConstants {
        public static final int RIGHT_ARM_MOTOR = 13;
        public static final int LEFT_ARM_MOTOR = 14;
        public static final double PID_P = 0.1;
        public static final double PID_I = 1e-4;
        public static final double PID_D = 1;
        public static final double PID_Iz = 0;
        public static final double PID_FF = 0;
        public static final double PID_MAX_OUTPUT_RANGE = 1;
        public static final double PID_MIN_OUTPUT_RANGE = -1;
        // Positions
        public static final double TOP_CONE_GOAL_SETPOINT = 0;
        public static final double TOP_CUBE_GOAL_SETPOINT = 0;
        public static final double MIDDLE_CONE_GOAL_SETPOINT = 0;
        public static final double MIDDLE_CUBE_GOAL_SETPOINT = 0;
        public static final double HOME_SETPOINT = 0;
        public static final double FLOOR_SETPOINT = 0;
    }
}
