// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;

public class Elevator extends SubsystemBase {
  /** Creates a new Elevator. */
    public enum PivotTarget{
    Retracted,
    Intake,
    Amp
  }
  

  TalonFX ElevatorRightMotor;
  PIDController ElevatorController;
  DutyCycleEncoder ElevatorThroughbore;
  CommandXboxController XboxController;
  TalonFXConfiguration ElevatorRightConfigs;
  TalonFXConfigurator ElevatorRightConfigurator;
  public Elevator(CommandXboxController xboxController) {
    XboxController = xboxController;
    // ElevatorLeftMotor = new TalonFX(Constants.ELEVATOR_LEFT_MOTOR);
    ElevatorRightMotor = new TalonFX(Constants.ELEVATOR_RIGHT_MOTOR);
    //region Configs
    // ElevatorLeftConfigs = new TalonFXConfiguration();
    ElevatorRightConfigs = new TalonFXConfiguration();
    // ElevatorLeftConfigurator = ElevatorLeftMotor.getConfigurator();
    ElevatorRightConfigurator = ElevatorRightMotor.getConfigurator();
    // ElevatorLeftConfigs.CurrentLimits.StatorCurrentLimit = 60;
    // ElevatorLeftConfigs.CurrentLimits.StatorCurrentLimitEnable = true;
    // ElevatorLeftConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    ElevatorRightConfigs.CurrentLimits.StatorCurrentLimit = 60;
    ElevatorRightConfigs.CurrentLimits.StatorCurrentLimitEnable = true;
    ElevatorRightConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    // ElevatorLeftConfigurator.apply(ElevatorLeftConfigs);
    ElevatorRightConfigurator.apply(ElevatorRightConfigs);
    //endregion
    ElevatorController = new PIDController(0.006, 0.0, 0.00);
    // ElevatorThroughbore = new DutyCycleEncoder(0, 360, Constants.PIVOT_OFFSET);

  }

  public void elevate(double speed){
    // ElevatorLeftMotor.set(speed);
    ElevatorRightMotor.set(speed);
  }

  public void off(){
    // ElevatorLeftMotor.set(0);
    ElevatorRightMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
