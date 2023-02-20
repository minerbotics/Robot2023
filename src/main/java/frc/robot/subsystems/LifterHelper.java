package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LifterHelperConstants;

public class LifterHelper extends SubsystemBase {

    private final DoubleSolenoid m_lifterHelperSolenoid;

    public LifterHelper() {
        m_lifterHelperSolenoid = new DoubleSolenoid(
            PneumaticsModuleType.CTREPCM, 
            LifterHelperConstants.FORWARD_CHANNEL, 
            LifterHelperConstants.REVERSE_CHANNEL
        );
    }

    public void raise() {
        m_lifterHelperSolenoid.set(Value.kReverse);
    }

    public void lower() {
        m_lifterHelperSolenoid.set(Value.kForward);
    }

    public void toggleLifter() {
        m_lifterHelperSolenoid.toggle();
    }

    public boolean isRaised() {
        return m_lifterHelperSolenoid.get() == Value.kReverse;
    }
}
