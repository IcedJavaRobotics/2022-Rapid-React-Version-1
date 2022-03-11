// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrainSubsystem extends SubsystemBase {
  /** Creates a new DriveTrainSubsystem. */

  final TalonFX frontLeftTalon = new TalonFX(Constants.FRONT_LEFT_TALON);
  final TalonFX backLeftTalon = new TalonFX(Constants.BACK_LEFT_TALON);
  final TalonFX frontRightTalon = new TalonFX(Constants.FRONT_RIGHT_TALON);
  final TalonFX backRightTalon = new TalonFX(Constants.BACK_RIGHT_TALON);
  double driveTime;
  double speedMod;

  public DriveTrainSubsystem() {

    frontLeftTalon.setInverted(true);
    backLeftTalon.setInverted(false);
    frontRightTalon.setInverted(true);
    backRightTalon.setInverted(false);

  }

  // Autonomous Section
  public void zeroEncoder() {

    frontLeftTalon.setSelectedSensorPosition(0);
    
  }

  public boolean autoForward() {
    if ( Math.abs(frontLeftTalon.getSelectedSensorPosition()) <= ((Constants.ROTATIONAL_CONSTANT / 2) * Constants.AUTO_DISTANCE_FORWARD) ) {
      spinMotorForward();
    } else {
      stopMotor();
      return true;
    }
    return false;
  }

  public void autoReverse() {

    if ( frontLeftTalon.getSelectedSensorPosition() >= 0 ) {
      // ((Constants.ROTATIONAL_CONSTANT / 2) * -Constants.AUTO_DISTANCE_BACKWARDS) 
      spinMotorBackwards();
    } else {
      stopMotor();
    }

  }

  public void spinMotorForward() {

    frontLeftTalon.set(ControlMode.PercentOutput, 0.5);
    backLeftTalon.set(ControlMode.PercentOutput, -0.5);
    frontRightTalon.set(ControlMode.PercentOutput, -0.5);
    backRightTalon.set(ControlMode.PercentOutput, 0.5);

    SmartDashboard.putNumber("Talon 4 Velocity", ( frontLeftTalon.getSelectedSensorVelocity() / Constants.ROTATIONAL_CONSTANT ) );
    SmartDashboard.putNumber("Talon 4 Position", ( frontLeftTalon.getSelectedSensorPosition() / Constants.ROTATIONAL_CONSTANT ) );

    /*
    frontLeftTalon.set(ControlMode.Velocity, Constants.ROTATIONAL_CONSTANT * 0.5);
    backLeftTalon.set(ControlMode.Velocity, Constants.ROTATIONAL_CONSTANT * 0.5);
    frontRightTalon.set(ControlMode.Velocity, Constants.ROTATIONAL_CONSTANT * 0.5);
    backLeftTalon.set(ControlMode.Velocity, Constants.ROTATIONAL_CONSTANT * 0.5);
    */

  }

  public void spinMotorBackwards() {

    frontLeftTalon.set(ControlMode.PercentOutput, -0.5);
    backLeftTalon.set(ControlMode.PercentOutput, 0.5);
    frontRightTalon.set(ControlMode.PercentOutput, 0.5);
    backRightTalon.set(ControlMode.PercentOutput, -0.5);

    SmartDashboard.putNumber("Talon 4 Velocity", ( frontLeftTalon.getSelectedSensorVelocity() / Constants.ROTATIONAL_CONSTANT ) );
    SmartDashboard.putNumber("Talon 4 Position", ( frontLeftTalon.getSelectedSensorPosition() / Constants.ROTATIONAL_CONSTANT ) );

  }

  public void  stopMotor() {
    
    frontLeftTalon.set(ControlMode.PercentOutput, 0);
    backLeftTalon.set(ControlMode.PercentOutput, 0);
    frontRightTalon.set(ControlMode.PercentOutput, 0);
    backRightTalon.set(ControlMode.PercentOutput, 0);

    SmartDashboard.putNumber("Talon 4 Velocity", ( frontLeftTalon.getSelectedSensorVelocity() / Constants.ROTATIONAL_CONSTANT ) );
    SmartDashboard.putNumber("Talon 4 Position", ( frontLeftTalon.getSelectedSensorPosition() / Constants.ROTATIONAL_CONSTANT ) );

  }

  // Teleop Section
  public void moveMotor( double speed, TalonFX talon) {

    talon.set(ControlMode.PercentOutput, speed);

  }

  public double ensureRange( double val ) {
    return Math.min(Math.max(val, -1), 1);
  }

  public void mecanumDrive( double R, double Y, double X ) {

    //Z = ( Z*0.4 ) + 0.6;

    if ( Math.abs(X) + Math.abs(Y) + Math.abs(R) == 0 ) {
      driveTime = Timer.getMatchTime();
    }

    if ( Timer.getMatchTime() - driveTime <= Constants.RAMP_UP_TIME ) {
      speedMod = (Timer.getMatchTime() - driveTime) / Constants.RAMP_UP_TIME;
    } else {
      speedMod = 1;
    }

    moveMotor( speedMod * ensureRange(X + Y + R), frontLeftTalon);
    moveMotor( speedMod * ensureRange(X - Y + R), backLeftTalon);
    moveMotor( speedMod * ensureRange(X - Y - R), frontRightTalon);
    moveMotor( speedMod * ensureRange(X + Y - R), backRightTalon);

    // moveMotor( (1 * ((X / Math.abs(X)) * (X * X)) + ((Y / Math.abs(Y)) * (Y * Y)) + ((R / Math.abs(R)) * (R * R))), frontLeftTalon );
    // moveMotor( (1 * ((X / Math.abs(X)) * (X * X)) - ((Y / Math.abs(Y)) * (Y * Y)) + ((R / Math.abs(R)) * (R * R))), backLeftTalon );
    // moveMotor( (1 * ((X / Math.abs(X)) * (X * X)) - ((Y / Math.abs(Y)) * (Y * Y)) - ((R / Math.abs(R)) * (R * R))), frontRightTalon );
    // moveMotor( (1 * ((X / Math.abs(X)) * (X * X)) + ((Y / Math.abs(Y)) * (Y * Y)) - ((R / Math.abs(R)) * (R * R))), backRightTalon );

    // System.out.println( "Talon Velocity: " + ( frontLeftTalon.getSelectedSensorVelocity() / Constants.ROTATIONAL_CONSTANT ) );
    // System.out.println( "Talon Position: " + ( frontLeftTalon.getSelectedSensorPosition() / Constants.ROTATIONAL_CONSTANT ) );

    SmartDashboard.putNumber("Talon 4 Velocity", ( frontLeftTalon.getSelectedSensorVelocity() / Constants.ROTATIONAL_CONSTANT ) );
    SmartDashboard.putNumber("Talon 4 Position", ( frontLeftTalon.getSelectedSensorPosition() / Constants.ROTATIONAL_CONSTANT ) );

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
