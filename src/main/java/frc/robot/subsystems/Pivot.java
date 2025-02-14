// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import frc.robot.Constants;

public class Pivot extends SubsystemBase {
  public enum PivotTarget{
    Retracted,
    Intake,
    Amp
  }
  TalonFX PivotMotor;
  PIDController PivotController;
  DutyCycleEncoder PivotThroughbore;
  CommandXboxController XboxController;
  TalonFXConfiguration PivotConfigs;
  TalonFXConfigurator PivotConfigurator;
  boolean intakePosition = false;
  boolean lock = true;

  /** Creates a new Pivot. */
  public Pivot(CommandXboxController xboxController) {
    XboxController = xboxController;
    PivotMotor = new TalonFX(Constants.PIVOT_MOTOR);
    //region Configs
    PivotConfigs = new TalonFXConfiguration();
    PivotConfigurator = PivotMotor.getConfigurator();
    PivotConfigs.CurrentLimits.StatorCurrentLimit = 60;
    PivotConfigs.CurrentLimits.StatorCurrentLimitEnable = true;
    PivotConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    PivotConfigurator.apply(PivotConfigs);
    //endregion
    PivotController = new PIDController(0.006, 0.0, 0.00);
    PivotThroughbore = new DutyCycleEncoder(Constants.PIVOT_ENCODER, 360, Constants.PIVOT_OFFSET);

  }

  public void pivot(double speed){
    PivotMotor.set(speed);
  }

  public void off(){
    PivotMotor.set(0);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
