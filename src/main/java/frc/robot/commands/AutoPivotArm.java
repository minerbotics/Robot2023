package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmPivot;

public class AutoPivotArm extends CommandBase {

    private final ArmPivot m_arm;

    public AutoPivotArm(ArmPivot arm) {
        m_arm = arm;
        addRequirements(m_arm);
    }

    @Override
    public void end(boolean interrupted) {
        m_arm.setMotor(0);
    }

    @Override
    public void execute() {
        m_arm.setMotor(0.5);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
    
}
