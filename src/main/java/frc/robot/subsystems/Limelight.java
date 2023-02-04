package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {

    NetworkTableEntry tx = null;
    NetworkTableEntry ty = null;
    NetworkTableEntry ta = null;

    //tv - whether the limelight has any valid targets (0 or 1)

    public Limelight() {
        

        
    }

    public void periodic() {

        
    }


    
}
