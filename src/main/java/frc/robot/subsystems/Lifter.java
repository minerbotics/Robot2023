package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LifterConstants;

public class Lifter extends SubsystemBase {

    private final DoubleSolenoid m_lifterSolenoid;

    public Lifter() {
        m_lifterSolenoid = new DoubleSolenoid(
            PneumaticsModuleType.CTREPCM, 
            LifterConstants.FORWARD_CHANNEL, 
            LifterConstants.REVERSE_CHANNEL
        );
    }

    public void raise() {
        m_lifterSolenoid.set(Value.kReverse);
    }

    public void lower() {
        m_lifterSolenoid.set(Value.kForward);
    }

    public void toggleLifter() {
        m_lifterSolenoid.toggle();
    }
}
