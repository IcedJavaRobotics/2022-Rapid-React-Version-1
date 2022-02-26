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

  final TalonFX frontLeftTalon;
  final TalonFX backLeftTalon;
  final TalonFX frontRightTalon;
  final TalonFX backRightTalon;

  public DriveTrainSubsystem() {

    frontLeftTalon = new TalonFX(Constants.FRONT_LEFT_TALON);
    backLeftTalon = new TalonFX(Constants.BACK_LEFT_TALON);
    frontRightTalon = new TalonFX(Constants.FRONT_RIGHT_TALON);
    backRightTalon = new TalonFX(Constants.BACK_RIGHT_TALON);

    frontLeftTalon.setInverted(true);
    backLeftTalon.setInverted(false);
    frontRightTalon.setInverted(true);
    backRightTalon.setInverted(false);

  }

  // Autonomous Section
  public void zeroEncoder() {

    frontLeftTalon.setSelectedSensorPosition(0);
    
  }

  public void autoSpin() {
    if ( frontLeftTalon.getSelectedSensorPosition() <= Constants.ROTATIONAL_CONSTANT * Constants.AUTO_DISTANCE) {
      spinMotor();
    } else {
      stopMotor();
    }
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

    talon.set(ControlMode.PercentOutput, 0.5);

  }

  public void mecanumDrive( double R, double Y, double X) {

    moveMotor( (X + Y + R), frontLeftTalon );
    moveMotor( (X - Y + R), backLeftTalon );
    moveMotor( (X - Y - R), frontRightTalon );
    moveMotor( (X + Y - R), backRightTalon );

    System.out.println( "Talon Velocity: " + frontLeftTalon.getSelectedSensorVelocity());
    System.out.println( "Talon Position: " + frontLeftTalon.getSelectedSensorPosition());

    SmartDashboard.putNumber("Talon 4 Velocity", frontLeftTalon.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Talon 4 Position", frontLeftTalon.getSelectedSensorPosition());

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
