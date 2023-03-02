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
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.AlignCenter;
import frc.robot.commands.AutoBackup;
import frc.robot.commands.AutoDrive;
import frc.robot.commands.Balance;
import frc.robot.commands.CycleArmDown;
import frc.robot.commands.CycleArmUp;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.ManualArmMove;
import frc.robot.commands.ToggleGrabber;
import frc.robot.commands.MinLift;
import frc.robot.commands.MaxLift;
import frc.robot.commands.ToggleSlide;
import frc.robot.commands.StopCommand;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Grabber;
import frc.robot.subsystems.Lifter;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Slider;
import frc.robot.subsystems.Arm.ArmState;

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
  private final Lifter m_lifter;
  private final Grabber m_grabber;
  private final Slider m_slider;
  private final Arm m_arm;

  // Controllers
  private final CommandXboxController m_primaryController;
  private final CommandXboxController m_secondaryController;

  // Buttons
  private Trigger yButtonPrimary;
  private Trigger backButtonPrimary;
  private Trigger bButtonPrimary;
  private Trigger xButtonSecondary;
  private Trigger yButtonSecondary;
  private Trigger aButtonSecondary;
  private Trigger backButtonSecondary;
  private Trigger startButtonSecondary;
  private Trigger lbButtonSecondary;
  private Trigger rbButtonSecondary;

  private ManualArmMove m_raiseArm;
  private AutoBackup m_autoBackup;

  private static SendableChooser<Command> m_chooser;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Subsystems
    m_drivetrainSubsystem = new DrivetrainSubsystem();
    m_Limelight = new Limelight();
    m_lifter = new Lifter();
    m_grabber = new Grabber();
    m_slider = new Slider();
    m_arm = new Arm();
    m_arm.setArmState(ArmState.STOW);

    // Controllers
    m_primaryController = new CommandXboxController(0);
    m_secondaryController = new CommandXboxController(1);

    //Buttons
    yButtonPrimary = m_primaryController.y();
    bButtonPrimary = m_primaryController.b();
    backButtonPrimary = m_primaryController.back();
    xButtonSecondary = m_secondaryController.x();
    yButtonSecondary = m_secondaryController.y();
    aButtonSecondary = m_secondaryController.a();
    backButtonSecondary = m_secondaryController.back();
    startButtonSecondary = m_secondaryController.start();
    lbButtonSecondary = m_secondaryController.leftBumper();
    rbButtonSecondary = m_secondaryController.rightBumper();

    //Commands
    m_raiseArm = new ManualArmMove(m_arm, m_secondaryController);
//    m_autoBackup = new AutoBackup(m_drivetrainSubsystem);

    m_arm.setDefaultCommand(m_raiseArm);

    // The controls are for field-oriented driving:
    // Left stick Y axis -> forward and backwards movement
    // Left stick X axis -> left and right movement
    // Right stick X axis -> rotation
    m_drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(
            m_drivetrainSubsystem,
            () -> -modifyAxis(m_primaryController.getLeftY()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(m_primaryController.getLeftX()) * DrivetrainSubsystem.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(m_primaryController.getRightX()) * DrivetrainSubsystem.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
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
    yButtonPrimary.whileTrue(new AlignCenter(m_drivetrainSubsystem, m_Limelight).withTimeout(0.1));
//    yButtonPrimary.onFalse(new StopCommand(m_drivetrainSubsystem));
    bButtonPrimary.whileTrue(new Balance(m_drivetrainSubsystem).withTimeout(0.1));
//    bButtonPrimary.onFalse(new StopCommand(m_drivetrainSubsystem));
    backButtonPrimary.onTrue(new RunCommand(m_drivetrainSubsystem::zeroGyroscope));

    // Secondary Driver
    yButtonSecondary.onTrue(new MaxLift(m_lifter));
    aButtonSecondary.onTrue(new MinLift(m_lifter));
    backButtonSecondary.onTrue(new CycleArmDown(m_arm, m_lifter));
//    startButtonSecondary.onTrue(new CycleArmUp(m_arm, m_lifter));
    lbButtonSecondary.onTrue(new ToggleSlide(m_slider));
    rbButtonSecondary.onTrue(new ToggleGrabber(m_grabber));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new InstantCommand();
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
