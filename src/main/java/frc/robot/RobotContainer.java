// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import java.util.Optional;

import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;

import choreo.Choreo;
import choreo.auto.AutoFactory;
import choreo.trajectory.SwerveSample;
import choreo.trajectory.Trajectory;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.DriveAuton;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ElevateAnalog;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Shintake;
import frc.robot.commands.PivotAnalog;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private final CommandXboxController m_controller = new CommandXboxController(Constants.DRIVE_CONTROLLER);
  private final CommandXboxController m_controller2 = new CommandXboxController(Constants.OPERATOR_CONTROLLER);
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  //region Subsystems
  private final AHRS m_ahrs = new AHRS(NavXComType.kMXP_SPI);
  // public final DrivetrainSubsystem m_drive = new DrivetrainSubsystem(m_ahrs);
  private final Pivot m_pivot = new Pivot(m_controller2);
  private final Elevator m_elevator = new Elevator(m_controller2);
  private final Shintake m_shintake = new Shintake();
  //endregion
  //region Trajectories
  Optional<Trajectory<SwerveSample>> testT = Choreo.loadTrajectory("Test");
  //endregion
  JoystickButton resetNavXButton = new JoystickButton(m_controller.getHID(), Constants.RESET_NAVX_BUTTON);
  JoystickButton x_Button = new JoystickButton(m_controller.getHID(), Constants.SHINTAKE_BUTTON);

  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    //region Drivetrain
      // m_controller.axisGreaterThan(0, 0.07).or(m_controller.axisGreaterThan(1, 0.07)).or(m_controller.axisGreaterThan(4, 0.07))
      // .or(m_controller.axisLessThan(0, -0.07)).or(m_controller.axisLessThan(1, -0.07)).or(m_controller.axisLessThan(4, -0.07))
      // .onTrue(new DriveCommand(m_drive, m_controller));
      // resetNavXButton.onTrue(new InstantCommand(m_drive::zeroGyroscope));
    //endregion

    //region Pivot
    //  ampPivotButton.whileTrue(new PivotIntake(m_pivot, PivotTarget.Amp));
    // m_controller2.axisGreaterThan(Constants.PIVOT_JOYSTICK, Constants.PIVOT_DEADBAND).or(m_controller2.axisLessThan(Constants.PIVOT_JOYSTICK, -Constants.PIVOT_DEADBAND)).onTrue(new PivotAnalog(m_pivot, m_controller2)).onFalse(new InstantCommand(m_pivot::off));
    //endregion
    //region Elevator
    // m_controller2.axisGreaterThan(Constants.ELEVATOR_JOYSTICK, Constants.ELEVATOR_DEADBAND).or(m_controller2.axisLessThan(Constants.ELEVATOR_JOYSTICK, -Constants.ELEVATOR_DEADBAND)).onTrue(new ElevateAnalog(m_elevator, m_controller2)).onFalse(new InstantCommand(m_elevator::off));
    //endregion
    m_controller2.axisGreaterThan(1, Constants.PIVOT_DEADBAND).or(m_controller2.axisLessThan(1, -Constants.PIVOT_DEADBAND)).onTrue(new ElevateAnalog(m_elevator, m_controller2)).onFalse(new InstantCommand(m_elevator::off));
    x_Button.whileTrue(new InstantCommand(m_shintake::alt_intake));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    Timer timer = new Timer();
    timer.start();
    // return new DriveAuton(m_drive, timer, testT);
    return null;
  }
}
