// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */

  private VictorSPX intakeMotor;

  public IntakeSubsystem() {

    intakeMotor = new VictorSPX(Constants.INTAKE_VICTOR);

  }

  public void ballPickup() {

    intakeMotor.set(ControlMode.PercentOutput, Constants.INTAKE_SPEED);

  }

  public void ballDrop() {

    intakeMotor.set(ControlMode.PercentOutput, -Constants.INTAKE_SPEED);
    

  }

  public void intakeStop() {

    intakeMotor.set(ControlMode.PercentOutput, 0);
    
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

