// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class VictorTestSubsystem extends SubsystemBase {
  /** Creates a new VictorTestSubsystem. */

  final Victor testVictor;
  
  public VictorTestSubsystem() {

    testVictor = new Victor(Constants.TESTVICTOR);
    
  }

  public void runVictor() {

    testVictor.set(Constants.VICTOR_SPEED);

  }

  public void stopVictor() {

    testVictor.set(0);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
