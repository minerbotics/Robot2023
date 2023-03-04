package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class AutoArm extends CommandBase {

    private final Arm m_arm;

    public AutoArm(Arm arm) {
        m_arm = arm;
        addRequirements(m_arm);
    }

    @Override
    public void execute() {
        m_arm.setPivotSpeed(0.25);
    }

    public void end() {
        m_arm.setStop();
    }
    
}
