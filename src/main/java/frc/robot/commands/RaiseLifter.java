package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lifter;

public class RaiseLifter extends CommandBase {

    private final Lifter m_lifter;

    public RaiseLifter(Lifter lifter) {
        m_lifter = lifter;
        addRequirements(m_lifter);
    }

    @Override
    public void initialize() {
        m_lifter.raise();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}