// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ElevatorSubsystem extends SubsystemBase {
  /** Creates a new ElevatorSubsystem. */
  
  private VictorSPX elevatorVictor = new VictorSPX(Constants.ELEVATOR_VICTOR);

  public ElevatorSubsystem() {}

  public void elevatorJoystick(double E) {
    
    if ( E >= 0.5 ) {
      elevatorUp();
    } else if ( E <= -0.5 ) {
      elevatorDown();
    } else {
      elevatorStop();
    }

  }

  public void elevatorUp() {

    elevatorVictor.set(ControlMode.PercentOutput, Constants.ELEVATOR_SPEED);

  }

  public void elevatorDown() {

    elevatorVictor.set(ControlMode.PercentOutput, - Constants.ELEVATOR_SPEED);

  }

  public void elevatorStop() {

    elevatorVictor.set(ControlMode.PercentOutput, 0);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
