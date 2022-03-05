// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrainSubsystem extends SubsystemBase {
  /** Creates a new DriveTrainSubsystem. */

  final TalonFX frontLeftTalon = new TalonFX(Constants.FRONT_LEFT_TALON);
  final TalonFX backLeftTalon = new TalonFX(Constants.BACK_LEFT_TALON);
  final TalonFX frontRightTalon = new TalonFX(Constants.FRONT_RIGHT_TALON);
  final TalonFX backRightTalon = new TalonFX(Constants.BACK_RIGHT_TALON);

  public DriveTrainSubsystem() {

    frontLeftTalon.setInverted(true);
    backLeftTalon.setInverted(true);
    frontRightTalon.setInverted(true);
    backRightTalon.setInverted(true);

  }

  // Autonomous Section
  public void zeroEncoder() {

    frontLeftTalon.setSelectedSensorPosition(0);
    
  }

  public boolean autoSpin() {
    if (frontLeftTalon.getSelectedSensorPosition() <= ( Constants.ROTATIONAL_CONSTANT / 2 ) * Constants.AUTO_DISTANCE) {
      spinMotor();
    } else {
      stopMotor();
      return true;
    }
    return false;
  }

  public void spinMotor() {

    frontLeftTalon.set(ControlMode.PercentOutput, 0.5);
    backLeftTalon.set(ControlMode.PercentOutput, 0.5);
    frontRightTalon.set(ControlMode.PercentOutput, 0.5);
    backLeftTalon.set(ControlMode.PercentOutput, 0.5);

  }

  public void  stopMotor() {
    
    frontLeftTalon.set(ControlMode.PercentOutput, 0);
    backLeftTalon.set(ControlMode.PercentOutput, 0);
    frontRightTalon.set(ControlMode.PercentOutput, 0);
    backRightTalon.set(ControlMode.PercentOutput, 0);

  }

  // Teleop Section
  public void moveMotor( double speed, TalonFX talon) {

    talon.set(ControlMode.PercentOutput, speed);

  }

  public void mecanumDrive( double R, double Y, double X) {

    moveMotor( ((X / Math.abs(X)) * (X * X)) + ((Y / Math.abs(Y)) * (Y * Y)) + ((R / Math.abs(R)) * (R * R)), frontLeftTalon );
    moveMotor( ((X / Math.abs(X)) * (X * X)) - ((Y / Math.abs(Y)) * (Y * Y)) + ((R / Math.abs(R)) * (R * R)), backLeftTalon );
    moveMotor( ((X / Math.abs(X)) * (X * X)) - ((Y / Math.abs(Y)) * (Y * Y)) - ((R / Math.abs(R)) * (R * R)), frontRightTalon );
    moveMotor( ((X / Math.abs(X)) * (X * X)) + ((Y / Math.abs(Y)) * (Y * Y)) - ((R / Math.abs(R)) * (R * R)), backRightTalon );

    System.out.println( "Talon Velocity: " + ( frontLeftTalon.getSelectedSensorVelocity() / Constants.ROTATIONAL_CONSTANT ) );
    System.out.println( "Talon Position: " + ( frontLeftTalon.getSelectedSensorPosition() / Constants.ROTATIONAL_CONSTANT ) );

    SmartDashboard.putNumber("Talon 4 Velocity", ( frontLeftTalon.getSelectedSensorVelocity() / Constants.ROTATIONAL_CONSTANT ) );
    SmartDashboard.putNumber("Talon 4 Position", ( frontLeftTalon.getSelectedSensorPosition() / Constants.ROTATIONAL_CONSTANT ) );

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
