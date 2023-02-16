package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Slider;

public class ToggleSlide extends CommandBase {

    private final Slider m_slider;

    public ToggleSlide(Slider slider) {
        m_slider = slider;
        addRequirements(m_slider);
    }

    @Override
    public void initialize() {
        m_slider.toggleSlider();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}