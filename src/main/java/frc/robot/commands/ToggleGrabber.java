package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Grabber;

public class ToggleGrabber extends CommandBase {

    private final Grabber m_grabber;

    public ToggleGrabber(Grabber grabber) {
        m_grabber = grabber;
        addRequirements(m_grabber);
    }

    @Override
    public void initialize() {
        m_grabber.toggleGrabber();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
