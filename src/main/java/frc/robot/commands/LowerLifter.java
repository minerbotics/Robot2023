package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lifter;

public class LowerLifter extends CommandBase {

    private final Lifter m_lifter;

    public LowerLifter(Lifter lifter) {
        m_lifter = lifter;
        addRequirements(m_lifter);
    }

    @Override
    public void initialize() {
        m_lifter.lower();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
