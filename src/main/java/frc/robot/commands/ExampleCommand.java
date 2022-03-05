// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class ExampleCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  
  private final DriveTrainSubsystem m_subsystem;
  private final ShooterSubsystem s_subsystem;
  private final ClimberSubsystem c_subsystem;
  // private final TalonFXTestSubsystem m_subsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ExampleCommand(DriveTrainSubsystem msubsystem, ShooterSubsystem ssubsystem, ClimberSubsystem csubsystem) {
  // public ExampleCommand(TalonFXTestSubsystem subsystem) {

    m_subsystem = msubsystem;
    s_subsystem = ssubsystem;
    c_subsystem = csubsystem;
    addRequirements(msubsystem);
    addRequirements(ssubsystem);
    addRequirements(csubsystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    m_subsystem.zeroEncoder();

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    m_subsystem.autoSpin();
    s_subsystem.autoShoot();
    if (m_subsystem.autoSpin() == true) {
      c_subsystem.climberUp();
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {

    s_subsystem.shootStop();
    c_subsystem.climberStop();

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    return false;
    
  }
}
