
package frc.robot.subsystems;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
    private NetworkTable m_limelightTable;
    private double ty, tx, ta, tv;

    public Limelight() {
        m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    }

    public void periodic() {
        
        ty = m_limelightTable.getEntry("ty").getDouble(0);
        tx = m_limelightTable.getEntry("tx").getDouble(0);
        ta = m_limelightTable.getEntry("ta").getDouble(0);
        tv = m_limelightTable.getEntry("tv").getDouble(0);
        //post to smart dashboard periodicallys
        boolean m_limelightHasValidTarget = (tv < 1.0) ? false : true;

        SmartDashboard.putString("time", ZonedDateTime.now( ZoneId.of( "America/Montreal" ) ).toString());
        SmartDashboard.putNumber("LimelightX", tx);
        SmartDashboard.putNumber("LimelightY", ty);
        SmartDashboard.putNumber("LimelightArea", ta);
        SmartDashboard.putBoolean("LimelightTarget", m_limelightHasValidTarget);
    }

    public double getTX() {
        return tx;
    }

    public double getTY() {
        return ty;
    }

    public double getTA() {
        return ta;
    }

    public double getTV() {
        return tv;
    }
}
