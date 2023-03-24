package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ArmPivot;
import frc.robot.subsystems.ArmTelescope;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Grabber;

public class DropItLikeItsHot extends SequentialCommandGroup {

    private DrivetrainSubsystem m_drivetrain;
    private ArmTelescope m_telescope;
    private ArmPivot m_arm;
    private Grabber m_grabber;
    
    public DropItLikeItsHot(DrivetrainSubsystem drivetrain, ArmTelescope telescope, ArmPivot arm, Grabber grabber) {
        m_drivetrain = drivetrain;
        m_telescope = telescope;
        m_arm = arm;
        m_grabber = grabber;
        addCommands(
            new AutoMoveTelescope(m_telescope, 0.5).withTimeout(0.5),
            new AutoPivotArm(m_arm).withTimeout(2),
            new AutoReleaseGrabber(m_grabber),
            new AutoDrive(m_drivetrain, new ChassisSpeeds(0.7, 0, 0)).withTimeout(7.5),
            new AutoDrive(m_drivetrain, new ChassisSpeeds(0, 0, 0))
        );

        addRequirements(m_drivetrain);
    }
}
