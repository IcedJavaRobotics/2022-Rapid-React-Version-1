// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    //DriveTrain
    public static final int FRONT_LEFT_TALON = 3;
    public static final int BACK_LEFT_TALON = 2;
    public static final int FRONT_RIGHT_TALON = 1;
    public static final int BACK_RIGHT_TALON = 4;
    public static final int ROTATIONAL_CONSTANT = 2048;
    public static final int AUTO_DISTANCE = 20;
        // in inches

    //Controllers
    public static final int CONTROLLER = 1;
    public static final int JOYSTICK = 0;
        // ^ these indicate the spot used on the driverstation ^
    public static final double DEADZONE = 0.2;

    //Test Victor
    public static final int TESTVICTOR = 7;
    public static final double VICTOR_SPEED = 0.2;

    //Test TalonFX
    public static final int TESTTALONFX = 8;

    //Intake
    public static final int RIGHT_INTAKE = 5;
    public static final int LEFT_INTAKE = 6;
    public static final double INTAKE_SPEED = 0.4;

    //Shooter
    public static final int BLINKIN_SPARK = 0;          //blinkin
    public static final int SHOOTER_MOTOR = 9;          //tbd
    public static final double SHOOTER_SPEED = 0.2;

}
