package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import frc.robot.Constants.ArmConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmTelescope extends SubsystemBase {

    private final CANSparkMax m_TelescopeMotor;

    public ArmTelescope() {
        m_TelescopeMotor = new CANSparkMax(
          ArmConstants.TELESCOPE_MOTOR, 
          CANSparkMaxLowLevel.MotorType.kBrushless);
    }

    @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void in(double speed) {
    m_TelescopeMotor.set(-speed);
  }

  public void stop() {
    m_TelescopeMotor.set(0);
  }

  public void out(double speed) {
    m_TelescopeMotor.set(speed);
  }

}
