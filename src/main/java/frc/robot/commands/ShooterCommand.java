// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends CommandBase {
  /** Creates a new ShooterCommand. */

  private final ShooterSubsystem shooterSubsystem;

  public ShooterCommand( ShooterSubsystem subsystem ) {
    shooterSubsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements( shooterSubsystem );
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    shooterSubsystem.checkY();
    shooterSubsystem.falseMaxSpeed();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    shooterSubsystem.ballLowerShoot();

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    shooterSubsystem.shootStop();
    shooterSubsystem.falseMaxSpeed();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}