package frc.robot.commands;


import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class Balance extends CommandBase {

    private final DrivetrainSubsystem  m_drivetrain;
    static final double kOffBalanceAngleThresholdDegrees = 10;
    static final double kOonBalanceAngleThresholdDegrees  = 5;

    private double xAxisRate;
    private double yAxisRate;
    private double pitchAngleDegrees;
    private double rollAngleDegrees;
    private boolean autoBalanceXMode;
    private boolean autoBalanceYMode;
    
    public Balance(DrivetrainSubsystem drivetrain) {
        m_drivetrain = drivetrain;
        addRequirements(m_drivetrain);
    }

    @Override
    public void execute() {
        pitchAngleDegrees = m_drivetrain.getPitchAngleDegrees();
        rollAngleDegrees = m_drivetrain.getRollAngleDegrees();

        //turns balance mode on if needed
        if ( !autoBalanceXMode && 
             (Math.abs(rollAngleDegrees) >= 
              Math.abs(kOffBalanceAngleThresholdDegrees))) {
            autoBalanceXMode = true;
        }
        else if ( autoBalanceXMode && 
                  (Math.abs(rollAngleDegrees) <= 
                   Math.abs(kOonBalanceAngleThresholdDegrees))) {
            autoBalanceXMode = false;
        }
        if ( !autoBalanceYMode && 
             (Math.abs(pitchAngleDegrees) >= 
              Math.abs(kOffBalanceAngleThresholdDegrees))) {
            autoBalanceYMode = true;
        }
        else if ( autoBalanceYMode && 
                  (Math.abs(pitchAngleDegrees) <= 
                   Math.abs(kOonBalanceAngleThresholdDegrees))) {
            autoBalanceYMode = false;
        }
        
    
        System.out.println("BalanceX: " + autoBalanceXMode + ", Roll: " + rollAngleDegrees);
        System.out.println("BalanceY: " + autoBalanceYMode + ", Pitch: " + pitchAngleDegrees);
        // Control drive system automatically, 
        // driving in reverse direction of pitch/roll angle,
        // with a magnitude based upon the angle
        
        //sets axis rates
        if ( autoBalanceYMode ) {
            double pitchAngleRadians = pitchAngleDegrees * (Math.PI / 180.0);
            yAxisRate = Math.sin(pitchAngleRadians) * -1;
            System.out.println("xaxisrate: " + yAxisRate);
        }
        if ( autoBalanceXMode ) {
            double rollAngleRadians = rollAngleDegrees * (Math.PI / 180.0);
            xAxisRate = Math.sin(rollAngleRadians) * -1;
            
            System.out.println("yaxisrate: " + xAxisRate);
        }

        //drives robot where needed
        m_drivetrain.drive(new ChassisSpeeds(xAxisRate * 1.6, yAxisRate * 1.6, 0.0));
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrain.drive(new ChassisSpeeds(0, 0, 0));
    }
}
