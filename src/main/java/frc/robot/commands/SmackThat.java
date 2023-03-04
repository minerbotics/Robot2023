package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Lifter;

public class SmackThat extends SequentialCommandGroup {
    
    private DrivetrainSubsystem m_drivetrain;
    private Lifter m_lifter;
    private Arm m_arm;
    
    public SmackThat(DrivetrainSubsystem drivetrain, Lifter lifter, Arm arm) {
        m_drivetrain = drivetrain;
        m_lifter = lifter;
        m_arm = arm;
        addCommands(
            new MaxLift(m_lifter).withTimeout(0.1),
            new AutoArm(m_arm).withTimeout(0.2),
            new AutoDrive(m_drivetrain, new ChassisSpeeds(-0.7, 0, 0)).withTimeout(7.5),
            new AutoDrive(m_drivetrain, new ChassisSpeeds(0, 0, 0))
        );

        addRequirements(m_drivetrain, m_lifter, m_arm);
    }
}
