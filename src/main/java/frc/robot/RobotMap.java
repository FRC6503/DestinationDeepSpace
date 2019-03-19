package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SpeedController;

public class RobotMap {
  public static SpeedController leftMotorA = new CANSparkMax(9, MotorType.kBrushless);
  public static SpeedController leftMotorB = new CANSparkMax(8, MotorType.kBrushless);

  public static SpeedController rightMotorA = new CANSparkMax(0, MotorType.kBrushless);
  public static SpeedController rightMotorB = new CANSparkMax(1, MotorType.kBrushless);
}
