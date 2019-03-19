package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;

public class Robot extends TimedRobot {
  public static ExampleSubsystem subsystem = new ExampleSubsystem();
  public static OI m_oi;

  Command autonomousCommand;
  SendableChooser<Command> chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_oi = new OI();
    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard.
   */
  @Override
  public void autonomousInit() {
    autonomousCommand = chooser.getSelected();

    // schedule the autonomous command (example)
    if (autonomousCommand != null) {
      autonomousCommand.start();
    }
  }

  public void teleOp2019() {
    // Scheduler.getInstance().run();
		final double speedMult = Math.max(Math.min(oi.driverJoystick.getTriggerAxis(GenericHID.Hand.kLeft), 0.7), 0.3);

		double fSpeed = deadzone(Robot.oi.driverJoystick.getX(GenericHID.Hand.kLeft)) * speedMult;
		double sSpeed = deadzone(Robot.oi.driverJoystick.getY(GenericHID.Hand.kLeft)) * speedMult;

		// DO NOT REMOVE THE CLAMPS!
		// I wIlL lItErAlLy DiE
		// 
		//  dddddddd
		//  dd    dd
		// dddd   dddddd	
		double lSpeed = Math.max(Math.min(fSpeed - sSpeed, 1), -1);
		double rSpeed = Math.max(Math.min(fSpeed + sSpeed, 1), -1);
		// dddd   dddddd
		//  dd    dd
		//  dddddddd
		//   
		if (lSpeed > 1 || lSpeed < -1 || rSpeed > 1 || rSpeed < -1) {
			System.out.println(greenText() + "AAAAAAAAAAAAAAA *wood crashing and meat grinding*");
		}

		drive.setSpeed(lSpeed, rSpeed);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
