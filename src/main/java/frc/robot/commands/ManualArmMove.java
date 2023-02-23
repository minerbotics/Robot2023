package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Arm;

public class ManualArmMove extends CommandBase {
    
    private final Arm m_arm;
    private CommandXboxController m_controller;

    public ManualArmMove(Arm arm, CommandXboxController controller) {
        m_arm = arm;
        m_controller = controller;
        addRequirements(m_arm);
    }

    @Override
    public void execute() {
        m_arm.setPivotSpeed(-m_controller.getLeftY());
    }

    public void end() {
        m_arm.setStop();
    }
}
