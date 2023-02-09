package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class StopCommand extends CommandBase {

    private final DrivetrainSubsystem m_driveTrain;

    public StopCommand(DrivetrainSubsystem driveTrain) {
        m_driveTrain = driveTrain;
        addRequirements(m_driveTrain);
    }

    @Override
    public void execute() {
        m_driveTrain.drive(new ChassisSpeeds(0, 0.0, 0.0));
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