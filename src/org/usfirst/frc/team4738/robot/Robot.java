
package org.usfirst.frc.team4738.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	Drive drive;
	Gamepad xboxXontroller;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    }
    
	/**
	 * 
	 * This function is called once before autonomous
	 * 
	 */
    public void autonomousInit() {

    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }
    
    
    /**
	 * 
	 * This function is called once before during operator control
	 * 
	 */
    public void teleopInit() {
    	drive = new Drive(0, 1);
    	xboxXontroller = new Gamepad(0);
    }

    
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
        drive.parabolicTank(xboxXontroller.getRawAxis(1), xboxXontroller.getRawAxis(5));
        if(xboxXontroller.getButtonDown(0)){
        	drive.parabolicTank(1, 0);
        } else{
        	drive.parabolicTank(0, 1);
        }
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
