package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Lifter;
import frc.robot.subsystems.Arm.ArmState;

public class CycleArmDown extends CommandBase {
    private final Arm m_arm;
    private final Lifter m_lifter;

    public CycleArmDown(Arm arm, Lifter lifter) {
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
        ArmState currentArmState = m_arm.getCurrentArmState();
        if (currentArmState != null && currentArmState == ArmState.STOW) {
            return;
        } else if (currentArmState == ArmState.HOME) {
            if (!m_lifter.isRaised()) {
                return;
            }
            m_arm.setPivotSetpoint(ArmConstants.STOW_SETPOINT);
            m_arm.setArmState(ArmState.STOW);
        } else if (currentArmState == ArmState.FLOOR) {
            m_arm.setPivotSetpoint(ArmConstants.HOME_SETPOINT);
            m_arm.setArmState(ArmState.HOME);
        } else if (currentArmState == ArmState.MID_CUBE) {
            m_arm.setPivotSetpoint(ArmConstants.FLOOR_SETPOINT);
            m_arm.setArmState(ArmState.FLOOR);
        } else if (currentArmState == ArmState.MID_CONE) {
            m_arm.setPivotSetpoint(ArmConstants.MIDDLE_CUBE_GOAL_SETPOINT);
            m_arm.setArmState(ArmState.MID_CUBE);
        } else if (currentArmState == ArmState.TOP_CUBE) {
            m_arm.setPivotSetpoint(ArmConstants.MIDDLE_CONE_GOAL_SETPOINT);
            m_arm.setArmState(ArmState.MID_CONE);
        } else if (currentArmState == ArmState.TOP_CONE) {
            m_arm.setPivotSetpoint(ArmConstants.TOP_CUBE_GOAL_SETPOINT);
            m_arm.setArmState(ArmState.TOP_CUBE);
        } else {
            return;
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
