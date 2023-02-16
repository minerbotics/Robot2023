package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Limelight;

public class AlignLeft extends CommandBase {

    private final DrivetrainSubsystem m_driveTrain;
    private final Limelight m_Limelight;

    public AlignLeft(DrivetrainSubsystem driveTrain, Limelight limelight) {
        m_driveTrain = driveTrain;
        m_Limelight = limelight;
        addRequirements(m_driveTrain, m_Limelight);
    }

    @Override
    public void execute() {
        if (m_Limelight.getTV() != 1.0) {
            return;
        }
        double tx = m_Limelight.getTX();
        if (tx < -14) {
            //strafe right
            m_driveTrain.drive(
                new ChassisSpeeds(
                    0.0,
                    0.1,
                    0.0));
        } else if (tx > -10) {
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