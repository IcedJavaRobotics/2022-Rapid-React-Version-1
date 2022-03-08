// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ExampleCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final DriveTrainSubsystem m_subsystem;
  private final ShooterSubsystem s_subsystem;
  private final ElevatorSubsystem e_subsystem;
  double time;
  double x;
  // private final TalonFXTestSubsystem m_subsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ExampleCommand(DriveTrainSubsystem msubsystem, ShooterSubsystem ssubsystem, ElevatorSubsystem esubsystem) {
  // public ExampleCommand(TalonFXTestSubsystem subsystem) {

    m_subsystem = msubsystem;
    s_subsystem = ssubsystem;
    e_subsystem = esubsystem;
    addRequirements(msubsystem);
    addRequirements(ssubsystem);
    addRequirements(esubsystem);
    x = 0;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    m_subsystem.zeroEncoder();
    s_subsystem.autoBlinkin();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (x == 0 ) {
      m_subsystem.autoForward();
      s_subsystem.autoShoot();
      System.out.println("a");
    }

    if (m_subsystem.autoForward() == true) {
      e_subsystem.elevatorUp();
      x = x + 1;
      System.out.println("b,1");
      System.out.println(x);
      SmartDashboard.putNumber("X equals", x);
    }

    if ( x == 1 ) {
      time = Timer.getMatchTime();
      System.out.println("c,2");
      SmartDashboard.putNumber("time equals", time);
    }

    if ( x >= 1) {
      if (Timer.getMatchTime() + Constants.ELEVATOR_TIME <= time ) {
        // e_subsystem.elevatorStop();
        m_subsystem.autoReverse();
        System.out.println("d,3");
      }
    }
    SmartDashboard.putNumber("Match time equals", Timer.getMatchTime());
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    s_subsystem.shootStop();
    e_subsystem.elevatorStop();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return false;
    
  }
}
