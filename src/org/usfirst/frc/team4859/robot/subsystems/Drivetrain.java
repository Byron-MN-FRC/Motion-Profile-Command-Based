/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4859.robot.subsystems;

import org.usfirst.frc.team4859.motionprofile.MotionProfileExample;

import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
	
	TalonSRX talon = new TalonSRX(1);
	TalonSRX follower = new TalonSRX(2);
	
	MotionProfileExample _example = new MotionProfileExample(talon);
	
	public Drivetrain() {
		drivetrainMotorConfig();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void runMotionProfile() {
		SetValueMotionProfile setOutput = _example.getSetValue();

		talon.set(ControlMode.MotionProfile, setOutput.value);
		follower.set(ControlMode.Follower, 1);
	}
	
	public void startMotionProfile() {
		_example.startMotionProfile();
	}
	
	public void endMotionProfile() {
		talon.set(ControlMode.PercentOutput, 0);
		/* clear our buffer and put everything into a known state */
		_example.reset();
	}
	
	public void drivetrainMotorConfig() {
		talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		talon.setSensorPhase(false); /* keep sensor and motor in phase */

		talon.config_kF(0, 0.12, 10);
		talon.config_kP(0, 0.24, 10);
		talon.config_kI(0, 0.0, 10);
		talon.config_kD(0, 0.0, 10);

		/* Our profile uses 10ms timing */
		talon.configMotionProfileTrajectoryPeriod(10, 10); 
		/*
		 * status 10 provides the trajectory target for motion profile AND
		 * motion magic
		 */
		talon.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 10);
	}
}
