package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Lifter;
import frc.robot.subsystems.LifterHelper;
import frc.robot.subsystems.Arm.ArmState;

public class CycleArmDown extends CommandBase {
    private final Arm m_arm;
    private final Lifter m_lifter;
    private final LifterHelper m_lifterHelper;

    public CycleArmDown(Arm arm, Lifter lifter, LifterHelper lifterHelper) {
        m_arm = arm;
        m_lifter = lifter;
        m_lifterHelper = lifterHelper;
        addRequirements(m_arm, m_lifter, m_lifterHelper);
    }

    @Override
    public void end(boolean interrupted) {
        m_arm.setStop();
    }

    @Override
    public void execute() {
        ArmState currentArmState = m_arm.getCurrentArmState();
        if (currentArmState == ArmState.HOME) {
            return;
        } else if (currentArmState == ArmState.FLOOR) {
            if (!m_lifter.isRaised() || !m_lifterHelper.isRaised()) {
                return;
            }
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
