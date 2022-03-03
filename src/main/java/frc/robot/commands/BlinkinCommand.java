// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class BlinkinCommand extends CommandBase {
  /** Creates a new Blinkin. */
  private final ShooterSubsystem ShooterSubsystem;
  public BlinkinCommand( ShooterSubsystem subsystem) {
    ShooterSubsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements( ShooterSubsystem );
  }
  int x = 0;
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    x = x+1;        //x counts upwards each time the button is pressed
      if(x == 1) {
      ShooterSubsystem.BlinkinBlueStart();      //First time button is pressed it's blue
      }

      if(x == 2){
        ShooterSubsystem.BlinkinRedStart();   //Second time the button is pressed it's red
      }

     if(x == 3){
      ShooterSubsystem.BlinkinEnd();        //Third time the button is pressed it ends.
      x = 0;
     }
    

     
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
