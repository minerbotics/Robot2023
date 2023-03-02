package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Lifter;
import frc.robot.subsystems.Arm.ArmState;

public class CycleArmUp extends CommandBase {
    private final Arm m_arm;
    private final Lifter m_lifter;

    public CycleArmUp(Arm arm, Lifter lifter) {
        m_arm = arm;
        m_lifter = lifter;
        addRequirements(m_arm, m_lifter);
    }

    @Override
    public void end(boolean interrupted) {
        m_arm.setStop();
    }

    @Override
    public void execute() {
        if (m_arm.getCurrentArmState() == ArmState.STOW) {
            if (!m_lifter.isRaised()) {
                System.out.println("Lifter not raised!!!");
                return;
            }
            m_arm.setPivotSetpoint(ArmConstants.HOME_SETPOINT);
            m_arm.setArmState(ArmState.HOME);
            System.out.println("Move to Home");
        } else if (m_arm.getCurrentArmState() == ArmState.HOME) {
            m_arm.setPivotSetpoint(ArmConstants.FLOOR_SETPOINT);
            m_arm.setArmState(ArmState.HOME);
        } else if (m_arm.getCurrentArmState() == ArmState.FLOOR) {
            m_arm.setPivotSetpoint(ArmConstants.MIDDLE_CUBE_GOAL_SETPOINT);
            m_arm.setArmState(ArmState.MID_CUBE);
        } else if (m_arm.getCurrentArmState() == ArmState.MID_CUBE) {
            m_arm.setPivotSetpoint(ArmConstants.MIDDLE_CONE_GOAL_SETPOINT);
            m_arm.setArmState(ArmState.MID_CONE);
        } else if (m_arm.getCurrentArmState() == ArmState.MID_CONE) {
            m_arm.setPivotSetpoint(ArmConstants.TOP_CUBE_GOAL_SETPOINT);
            m_arm.setArmState(ArmState.TOP_CUBE);
        } else if (m_arm.getCurrentArmState() == ArmState.TOP_CUBE) {
            m_arm.setPivotSetpoint(ArmConstants.TOP_CONE_GOAL_SETPOINT);
            m_arm.setArmState(ArmState.TOP_CONE);
        }
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
