package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.Arm;

public class ArmTopPosition extends CommandBase {
    private final Arm m_arm;

    public ArmTopPosition(Arm arm) {
        m_arm = arm;
        addRequirements(m_arm);
    }

    @Override
    public void end(boolean interrupted) {
        m_arm.setStop();
    }

    @Override
    public void execute() {
        m_arm.setPivotSetpoint(ArmConstants.TOP_GOAL_SETPOINT);
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        super.initialize();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    
}
