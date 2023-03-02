package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoDrive extends CommandBase {

    private final DrivetrainSubsystem m_drivetrain;
    private ChassisSpeeds m_chassisSpeeds;
    
    public AutoDrive(DrivetrainSubsystem drivetrain, ChassisSpeeds chassisSpeeds) {
        m_drivetrain = drivetrain;
        m_chassisSpeeds = chassisSpeeds;
        addRequirements(m_drivetrain);
    }

    @Override
    public void execute() {
        m_drivetrain.drive(m_chassisSpeeds);
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrain.drive(new ChassisSpeeds(0,0,0));
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
