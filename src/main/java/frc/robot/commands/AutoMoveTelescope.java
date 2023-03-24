package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmTelescope;

public class AutoMoveTelescope extends CommandBase {
    
    private final ArmTelescope m_Telescope;
    private double m_amount;

    public AutoMoveTelescope(ArmTelescope telescope, double amount) {
        m_Telescope = telescope;
        m_amount = amount;
        addRequirements(m_Telescope);
    }

    @Override
    public void end(boolean interrupted) {
        m_Telescope.stop();
    }

    @Override
    public void execute() {
        m_Telescope.move(m_amount);
    }
}
