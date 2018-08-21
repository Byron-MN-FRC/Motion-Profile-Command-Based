/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4859.robot.subsystems;

import com.ctre.phoenix.motion.SetValueMotionProfile;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteFeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
	
	TalonSRX talonRight = new TalonSRX(1);
	TalonSRX followerRight = new TalonSRX(2);
	TalonSRX talonLeft = new TalonSRX(4);
	TalonSRX followerLeft = new TalonSRX(5);
	
	PigeonIMU pigeon = new PigeonIMU(2);
	
	MotionProfileExample _exampleRight = new MotionProfileExample(talonRight, false);
	MotionProfileExample _exampleLeft = new MotionProfileExample(talonLeft, true);
	
	public Drivetrain() {
		drivetrainMotorConfigRight();
		drivetrainMotorConfigLeft();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void runMotionProfileRight() {
		SetValueMotionProfile setOutput = _exampleRight.getSetValue();

		talonRight.set(ControlMode.MotionProfile, setOutput.value);
		followerRight.set(ControlMode.Follower, 1);
		
		_exampleRight.control();
	}
	
	public void runMotionProfileLeft() {
		SetValueMotionProfile setOutput = _exampleLeft.getSetValue();

		talonLeft.set(ControlMode.MotionProfile, setOutput.value);
		followerLeft.set(ControlMode.Follower, 1);
		
		_exampleLeft.control();
	}
	
	public void startMotionProfileRight() {
		_exampleRight.startMotionProfile();
	}
	
	public void startMotionProfileLeft() {
		_exampleLeft.startMotionProfile();
	}
	
	public void endMotionProfileRight() {
		talonRight.set(ControlMode.PercentOutput, 0);
		/* clear our buffer and put everything into a known state */
		_exampleRight.reset();
	}
	
	public void endMotionProfileLeft() {
		talonLeft.set(ControlMode.PercentOutput, 0);
		/* clear our buffer and put everything into a known state */
		_exampleLeft.reset();
	}
	
	public void drivetrainMotorConfigRight() {
		talonRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		talonRight.setSensorPhase(false); /* keep sensor and motor in phase */

		// Encoder PID
		talonRight.config_kF(0, 0.107, 10);
		talonRight.config_kP(0, 0.3, 10);
		talonRight.config_kI(0, 0.0, 10);
		talonRight.config_kD(0, 0.0, 10);
		
		// Pigeon P
		talonRight.config_kP(1, 1.0, 10);

		/* Our profile uses 10ms timing */
		talonRight.configMotionProfileTrajectoryPeriod(10, 10); 
		/*
		 * status 10 provides the trajectory target for motion profile AND
		 * motion magic
		 */
		talonRight.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 10);
		
		talonRight.setInverted(true);
		followerRight.setInverted(true);
		
		talonRight.configRemoteFeedbackFilter(pigeon.getDeviceID(), RemoteSensorSource.Pigeon_Yaw, 0, 10);
		talonRight.configSelectedFeedbackSensor(RemoteFeedbackDevice.RemoteSensor0, 1, 10);
	}
	
	public void drivetrainMotorConfigLeft() {
		talonLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		talonLeft.setSensorPhase(false); /* keep sensor and motor in phase */

		// Encoder PID
		talonLeft.config_kF(0, 0.107, 10);
		talonLeft.config_kP(0, 0.3, 10);
		talonLeft.config_kI(0, 0.0, 10);
		talonLeft.config_kD(0, 0.0, 10);
		
		// Pigeon P
		talonLeft.config_kP(1, 1.0, 10);

		/* Our profile uses 10ms timing */
		talonLeft.configMotionProfileTrajectoryPeriod(10, 10); 
		/*
		 * status 10 provides the trajectory target for motion profile AND
		 * motion magic
		 */
		talonLeft.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, 10);
		
		talonLeft.configRemoteFeedbackFilter(pigeon.getDeviceID(), RemoteSensorSource.Pigeon_Yaw, 0, 10);
		talonLeft.configSelectedFeedbackSensor(RemoteFeedbackDevice.RemoteSensor0, 1, 10);
		
	}
}
