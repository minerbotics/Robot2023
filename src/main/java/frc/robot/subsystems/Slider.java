package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SliderConstants;

public class Slider extends SubsystemBase {

    private final DoubleSolenoid m_sliderSolenoid;

    public Slider() {
        m_sliderSolenoid = new DoubleSolenoid(
            PneumaticsModuleType.CTREPCM, 
            SliderConstants.FORWARD_CHANNEL, 
            SliderConstants.REVERSE_CHANNEL
        );
    }

    public void slideIn() {
        m_sliderSolenoid.set(Value.kForward);
    }

    public void slideOut() {
        m_sliderSolenoid.set(Value.kReverse);
    }

    public void toggleSlider() {
        String sliderPosition;
        if (this.isOut()) {
            sliderPosition = "Out";
            this.slideIn();
        } else {
            sliderPosition = "In";
            this.slideOut();
        }
        SmartDashboard.putString("Slider Position", sliderPosition);
    }

    public boolean isOut() {
        return m_sliderSolenoid.get() == Value.kReverse;
    }
}
