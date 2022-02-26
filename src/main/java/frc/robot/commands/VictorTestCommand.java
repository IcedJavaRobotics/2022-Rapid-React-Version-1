// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.VictorTestSubsystem;

public class VictorTestCommand extends CommandBase {
  /** Creates a new VictorTestCommand. */
  
  private final VictorTestSubsystem victorTestSubsystem;

  public VictorTestCommand( VictorTestSubsystem subsystem ) {
    // Use addRequirements() here to declare subsystem dependencies.

    victorTestSubsystem = subsystem;

    addRequirements( victorTestSubsystem );

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    victorTestSubsystem.runVictor();

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    victorTestSubsystem.stopVictor();
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
