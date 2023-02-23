package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.GrabberConstants;

public class Grabber extends SubsystemBase {

    private final DoubleSolenoid m_grabberSolenoid;

    public Grabber() {
        m_grabberSolenoid = new DoubleSolenoid(
            PneumaticsModuleType.CTREPCM, 
            GrabberConstants.FORWARD_CHANNEL, 
            GrabberConstants.REVERSE_CHANNEL
        );
    }

    public void grab() {
        m_grabberSolenoid.set(Value.kForward);
    }

    public void release() {
        m_grabberSolenoid.set(Value.kReverse);
    }

    public void toggleGrabber() {
        if (this.isGrabbing()) {
            this.release();
        } else {
            this.grab();
        }
    }

    public boolean isGrabbing() {
        return m_grabberSolenoid.get() == Value.kForward;
    }
}
