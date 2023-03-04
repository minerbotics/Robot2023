package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoBackup extends SequentialCommandGroup {

    private DrivetrainSubsystem m_drivetrain;
    
    public AutoBackup(DrivetrainSubsystem drivetrain) {
        m_drivetrain = drivetrain;
        addCommands(
            new AutoDrive(m_drivetrain, new ChassisSpeeds(0.7, 0, 0)).withTimeout(7.5),
            new AutoDrive(m_drivetrain, new ChassisSpeeds(0, 0, 0))
        );

        addRequirements(m_drivetrain);
    }
}
