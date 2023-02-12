package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Grabber;
import frc.robot.subsystems.Slider;

public class GrabCone extends CommandBase {

    private final Grabber m_grabber;
    private final Slider m_slider;

    public GrabCone(Grabber grabber, Slider slider) {
        m_grabber = grabber;
        m_slider = slider;
        addRequirements(m_grabber, m_slider);
    }

    @Override
    public void initialize() {
        if(m_slider.isOut()) {
            m_slider.slideIn();
        }
        m_grabber.grab();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
