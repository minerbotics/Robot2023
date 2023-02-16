package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lifter;
import frc.robot.subsystems.LifterHelper;

public class MinLift extends CommandBase {

    private final Lifter m_lifter;
    private final LifterHelper m_lifterHelper;

    public MinLift(Lifter lifter, LifterHelper lifterHelper) {
        m_lifter = lifter;
        m_lifterHelper = lifterHelper;
        addRequirements(m_lifter, m_lifterHelper);
    }

    @Override
    public void initialize() {
        m_lifter.lower();
        m_lifterHelper.lower();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
