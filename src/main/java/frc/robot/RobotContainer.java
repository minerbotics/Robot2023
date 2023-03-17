// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.AlignCenter;
import frc.robot.commands.AutoBackup;
import frc.robot.commands.Balance;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.MoveTelescope;
import frc.robot.commands.PivotArm;
import frc.robot.commands.ToggleGrabber;
import frc.robot.subsystems.ArmPivot;
import frc.robot.subsystems.ArmTelescope;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Grabber;
import frc.robot.subsystems.Limelight;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Subsystems
  private final DrivetrainSubsystem m_drivetrainSubsystem;
  private final Limelight m_Limelight;
  private final Grabber m_Grabber;
  private final ArmPivot m_ArmPivot;
  private final ArmTelescope m_Telescope;

  // Controllers
  private final CommandXboxController m_DriverController;
  private final CommandXboxController m_OperatorController;

  // Commands
  private PivotArm m_Pivot;
  private MoveTelescope m_Extend;

  private AutoBackup m_autoBackup;

  private static SendableChooser<Command> m_chooser;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Subsystems
    m_drivetrainSubsystem = new DrivetrainSubsystem();
    m_Limelight = new Limelight();
    m_Grabber = new Grabber();
    m_ArmPivot = new ArmPivot();
    m_Telescope = new ArmTelescope();

    // Controllers
    m_DriverController = new CommandXboxController(0);
    m_OperatorController = new CommandXboxController(1);

    //Commands
    m_Pivot = new PivotArm(m_ArmPivot, m_OperatorController);
    m_ArmPivot.setDefaultCommand(m_Pivot);

    m_Extend = new MoveTelescope(m_Telescope, m_OperatorController);
    m_Telescope.setDefaultCommand(m_Extend);
    m_autoBackup = new AutoBackup(m_drivetrainSubsystem);

    // The controls are for field-oriented driving:
    // Left stick Y axis -> forward and backwards movement
    // Left stick X axis -> left and right movement
    // Right stick X axis -> rotation
    m_drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(
            m_drivetrainSubsystem,
            () -> -modifyAxis(m_DriverController.getLeftY()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(m_DriverController.getLeftX()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(m_DriverController.getRightX()) * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
    ));

    m_chooser = new SendableChooser<Command>();
    m_chooser.setDefaultOption("Back up", m_autoBackup);
    
    SmartDashboard.putData("Auto Choices", m_chooser);
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Primary Driver
    m_DriverController.y().whileTrue(new AlignCenter(m_drivetrainSubsystem, m_Limelight).withTimeout(0.1));
    m_DriverController.b().whileTrue(new Balance(m_drivetrainSubsystem).withTimeout(0.1));
    m_DriverController.start().onTrue(new InstantCommand(() -> m_drivetrainSubsystem.zeroGyroscope()));

    // Secondary Driver
    m_OperatorController.rightBumper().onTrue(new ToggleGrabber(m_Grabber));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }

  private static double deadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }

  private static double modifyAxis(double value) {
    // Deadband
    value = deadband(value, 0.05);

    // Square the axis
    value = Math.copySign(value * value, value);

    return value;
  }
}
