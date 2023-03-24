package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Limelight;

public class AutoAlign extends CommandBase {

    private final DrivetrainSubsystem m_driveTrain;
    private final Limelight m_Limelight;
    private final CommandXboxController m_Controller;

    public AutoAlign(DrivetrainSubsystem driveTrain, Limelight limelight, CommandXboxController controller) {
        m_driveTrain = driveTrain;
        m_Limelight = limelight;
        m_Controller = controller;
        addRequirements(m_driveTrain, m_Limelight);
    }

    @Override
    public void execute() {
        if (m_Limelight.getTV() != 1.0) {
            return;
        }
        double tx = m_Limelight.getTX();
        if (tx < -2) {
            //strafe right
            m_driveTrain.drive(
                new ChassisSpeeds(
                    0.0,
                    0.1,
                    0.0));
        } else if (tx > 2) {
            //strafe left
            m_driveTrain.drive(
                new ChassisSpeeds(
                    0.0,
                    -0.1,
                    0.0));
        } else {
            m_driveTrain.drive(
                new ChassisSpeeds(
                    0.0,
                    0.0,
                    0.0));
        }
        if (m_Limelight.getTV() != 1.0) {
            return;
        }
        float yaw = m_driveTrain.getYaw();
        if (yaw < -0.005) {
            //strafe right
            m_driveTrain.drive(
                new ChassisSpeeds(
                    0.0,
                    0.0,
                    0.025));
        } else if (yaw > 0.005) {
            //strafe left
            m_driveTrain.drive(
                new ChassisSpeeds(
                    0.0,
                    0.0,
                    0.025));
        } else {
            m_driveTrain.drive(
                new ChassisSpeeds(
                    0.0,
                    0.0,
                    0.0));
        }
        if (m_Limelight.getTV() != 1.0) {
            return;
        }
        /*
        double tv = m_Limelight.getTV();
        if (tv < -2) {
            //strafe right
            m_driveTrain.drive(
                new ChassisSpeeds(
                    0.1,
                    0.0,
                    0.0));
        } else if (tv > 2) {
            //strafe left
            m_driveTrain.drive(
                new ChassisSpeeds(
                    -0.1,
                    -0.0,
                    0.0));
        } else {
            m_driveTrain.drive(
                new ChassisSpeeds(
                    0.0,
                    0.0,
                    0.0));
        }
        */
    }

    @Override
    public void initialize() {

    }

    @Override
    public void end(boolean interrupted) {

    }

  // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
} 