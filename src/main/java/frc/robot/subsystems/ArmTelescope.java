package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.RelativeEncoder;

import frc.robot.Constants.ArmConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmTelescope extends SubsystemBase {

  private final CANSparkMax m_TelescopeMotor;
  private final RelativeEncoder m_Encoder;

  public ArmTelescope() {
    m_TelescopeMotor = new CANSparkMax(
      ArmConstants.TELESCOPE_MOTOR, 
      CANSparkMaxLowLevel.MotorType.kBrushless);
    m_Encoder = m_TelescopeMotor.getEncoder();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Telescope Position", m_Encoder.getPosition());
  }

  public void stop() {
    m_TelescopeMotor.set(0);
  }

  public void move(double speed) {
    m_TelescopeMotor.set(speed * 0.6);
  }

}
