package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Grabber;
import frc.robot.subsystems.Slider;

public class GrabGamePiece extends CommandBase {

    private final Grabber m_grabber;
    private final Slider m_slider;

    public GrabGamePiece(Grabber grabber, Slider slider) {
        m_grabber = grabber;
        m_slider = slider;
        addRequirements(m_grabber, m_slider);
    }

    @Override
    public void initialize() {
        if(!m_slider.isOut()) {
            return;
        }
        m_grabber.grab();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
