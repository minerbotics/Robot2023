package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ArmConstants;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Lifter;
import frc.robot.subsystems.LifterHelper;

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
        double position = m_arm.getPosition();
        if (position == ArmConstants.HOME_SETPOINT) {
            return;
        } else if (position == ArmConstants.FLOOR_SETPOINT) {
            if (!m_lifter.isRaised() || !m_lifterHelper.isRaised()) {
                return;
            }
            m_arm.setPivotSetpoint(ArmConstants.HOME_SETPOINT);
        } else if (position == ArmConstants.MIDDLE_CUBE_GOAL_SETPOINT) {
            m_arm.setPivotSetpoint(ArmConstants.FLOOR_SETPOINT);
        } else if (position == ArmConstants.MIDDLE_CONE_GOAL_SETPOINT) {
            m_arm.setPivotSetpoint(ArmConstants.MIDDLE_CUBE_GOAL_SETPOINT);
        } else if (position == ArmConstants.TOP_CUBE_GOAL_SETPOINT) {
            m_arm.setPivotSetpoint(ArmConstants.MIDDLE_CONE_GOAL_SETPOINT);
        } else if (position == ArmConstants.TOP_CONE_GOAL_SETPOINT) {
            m_arm.setPivotSetpoint(ArmConstants.TOP_CUBE_GOAL_SETPOINT);
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
