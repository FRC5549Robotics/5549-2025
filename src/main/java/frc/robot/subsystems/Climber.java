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

public class Climber extends SubsystemBase {
  SparkMax ClimberMotor;
  CommandXboxController XboxController;
  boolean intakePosition = false;
  boolean lock = true;

  /** Creates a new Pivot. */
  public Climber(CommandXboxController xboxController) {
    XboxController = xboxController;
    ClimberMotor = new SparkMax(Constants.CLIMBER_MOTOR, MotorType.kBrushless);
  }

  public void climb(double speed){
    ClimberMotor.set(speed);
  }

  public void off(){
    ClimberMotor.set(0);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}