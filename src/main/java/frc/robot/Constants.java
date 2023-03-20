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
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(52.21);

    public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 1; 
    public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 2; 
    public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 11; 
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(129.46); 

    public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 7; 
    public static final int BACK_LEFT_MODULE_STEER_MOTOR = 8; 
    public static final int BACK_LEFT_MODULE_STEER_ENCODER = 10;
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(173.93);

    public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 3;
    public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 4;
    public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 12;
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(8.87);

    public static final double TRANSLATION_SLEW = 1.55;
    public static final double ROTATION_SLEW = 3.00;

    public static final int PCM_ID = 0;

    public static final class ArmConstants {
        public static final int PIVOT_MOTOR_RIGHT = 13;
        public static final int PIVOT_MOTOR_LEFT = 14;
        public static final int TELESCOPE_MOTOR = 15;
        public static final double maxMotorVoltage = 12;

        public static double kP;
        public static double kI;
        public static double kD;
        
        public static double kS;
        public static double kV;
        public static double kA;
        public static double kG;

        public enum Position {
            STOWED(0.0),
            FLOOR(1.0),
            MID(2.0),
            TOP(3.0),
            LOAD(3.5);

            private double pivotPos;

            private Position(double pivot) {
                this.pivotPos = pivot;
            }

            public double getPivot() {
                return pivotPos;
            }
        }
    }

    public static final class GrabberConstants {
        public static final int FORWARD_CHANNEL = 6;
        public static final int REVERSE_CHANNEL = 7;
    }
}
