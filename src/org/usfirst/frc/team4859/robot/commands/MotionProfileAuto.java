/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4859.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4859.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class MotionProfileAuto extends Command {
	public MotionProfileAuto() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.drivetrain.runMotionProfileRight();
		Robot.drivetrain.startMotionProfileRight();
		Robot.drivetrain.runMotionProfileLeft();
		Robot.drivetrain.startMotionProfileLeft();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.drivetrain.runMotionProfileRight();
		Robot.drivetrain.runMotionProfileLeft();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.drivetrain.endMotionProfileRight();
		Robot.drivetrain.endMotionProfileLeft();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.drivetrain.endMotionProfileRight();
		Robot.drivetrain.endMotionProfileLeft();
	}
}
