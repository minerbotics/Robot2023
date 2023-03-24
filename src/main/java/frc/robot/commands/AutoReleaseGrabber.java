package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Grabber;

public class AutoReleaseGrabber extends CommandBase {

    private final Grabber m_Grabber;

    public AutoReleaseGrabber(Grabber grabber) {
        m_Grabber = grabber;
        addRequirements(m_Grabber);
    }

    @Override
    public void initialize() {
        if (m_Grabber.isGrabbing()) {
            m_Grabber.toggleGrabber();
        } else if (!m_Grabber.isGrabbing()) {
            m_Grabber.toggleGrabber();
            m_Grabber.toggleGrabber();
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }
    
}
