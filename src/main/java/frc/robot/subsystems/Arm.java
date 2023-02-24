package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmConstants;

public class Arm extends SubsystemBase {

    private final CANSparkMax m_rightArmMotor;
    private final CANSparkMax m_leftArmMotor;
    private final SparkMaxPIDController m_pidController;
    private final RelativeEncoder m_encoder;

    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

    public enum ArmState {
        TOP_CONE,
        TOP_CUBE,
        MID_CONE,
        MID_CUBE,
        FLOOR,
        HOME;
    }

    private ArmState m_currentArmState;

    public Arm() {
        m_rightArmMotor = new CANSparkMax(
            ArmConstants.RIGHT_ARM_MOTOR, 
            CANSparkMaxLowLevel.MotorType.kBrushless);
        m_leftArmMotor = new CANSparkMax(
            ArmConstants.LEFT_ARM_MOTOR, 
            CANSparkMaxLowLevel.MotorType.kBrushless);
        m_leftArmMotor.follow(m_rightArmMotor, true);
        
        // Get the PID controller for the pivot motor
        m_pidController = m_rightArmMotor.getPIDController();

        m_encoder = m_rightArmMotor.getEncoder();

        // set PID coefficients
        m_pidController.setP(ArmConstants.PID_P);
        m_pidController.setI(ArmConstants.PID_I);
        m_pidController.setD(ArmConstants.PID_D);
        m_pidController.setIZone(ArmConstants.PID_Iz);
        m_pidController.setFF(ArmConstants.PID_FF);
        m_pidController.setOutputRange(
            ArmConstants.PID_MIN_OUTPUT_RANGE, 
            ArmConstants.PID_MAX_OUTPUT_RANGE);
    }

    public void setPivotSetpoint(double setpoint) {
        m_pidController.setReference(setpoint, ControlType.kPosition);
    }

    public double getPosition() {
        return m_encoder.getPosition();
    }

    public void setPivotSpeed(double speed) {
        m_rightArmMotor.set(speed * 0.25);
    }

    public void resetPivot() {
        m_pidController.setIAccum(0.0);
    }

    public void setStop() {
        m_rightArmMotor.set(0);
    }

    public ArmState getCurrentArmState() {
        return m_currentArmState;
    }

    public void setArmState(ArmState armState) {
        m_currentArmState = armState;
    }
}
