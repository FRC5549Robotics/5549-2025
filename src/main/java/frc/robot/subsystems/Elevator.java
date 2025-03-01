// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;

public class Elevator extends SubsystemBase {
  /** Creates a new Elevator. */

  TalonFX ElevatorRightMotor;
  TalonFX ElevatorLeftMotor;
  PIDController ElevatorController;
  DutyCycleEncoder ElevatorThroughbore;
  CommandXboxController XboxController;
  TalonFXConfiguration ElevatorRightConfigs;
  TalonFXConfigurator ElevatorRightConfigurator;
  TalonFXConfiguration ElevatorLeftConfigs;
  TalonFXConfigurator ElevatorLeftConfigurator;
  boolean reset = true;
  
  public Elevator(CommandXboxController xboxController) {
    XboxController = xboxController;
    ElevatorLeftMotor = new TalonFX(Constants.ELEVATOR_LEFT_MOTOR);
    ElevatorRightMotor = new TalonFX(Constants.ELEVATOR_RIGHT_MOTOR);
    //region Configs
    ElevatorLeftConfigs = new TalonFXConfiguration();
    ElevatorRightConfigs = new TalonFXConfiguration();
    ElevatorLeftConfigurator = ElevatorLeftMotor.getConfigurator();
    ElevatorRightConfigurator = ElevatorRightMotor.getConfigurator();
    ElevatorLeftConfigs.CurrentLimits.StatorCurrentLimit = 60;
    ElevatorLeftConfigs.CurrentLimits.StatorCurrentLimitEnable = true;
    ElevatorLeftConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    ElevatorRightConfigs.CurrentLimits.StatorCurrentLimit = 60;
    ElevatorRightConfigs.CurrentLimits.StatorCurrentLimitEnable = true;
    ElevatorRightConfigs.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    ElevatorLeftConfigurator.apply(ElevatorLeftConfigs);
    ElevatorRightConfigurator.apply(ElevatorRightConfigs);
    //endregion
    ElevatorController = new PIDController(0.6, 0.0, 0.00);
    ElevatorThroughbore = new DutyCycleEncoder(0, 360, Constants.PIVOT_OFFSET);
      
    };
  

  public void elevate(double speed){
    ElevatorLeftMotor.set(speed);
    ElevatorRightMotor.set(-speed);
  }

  public void off(){
    ElevatorLeftMotor.set(0);
    ElevatorRightMotor.set(0);
  }

  public void ElevateToSetpoint(double leftElevatorSetpoint, double rightElevatorSetpoint) {
    ElevatorLeftMotor.set(ElevatorController.calculate(getLeftElevatorPosition(), leftElevatorSetpoint)); 
    ElevatorRightMotor.set(ElevatorController.calculate(getRightElevatorPosition(), rightElevatorSetpoint));
  }

  public double getLeftElevatorPosition() {
    return ElevatorLeftMotor.getPosition().getValueAsDouble(); 
  }
  public double getRightElevatorPosition() {
    return ElevatorRightMotor.getPosition().getValueAsDouble(); 
  }

  public double detectElevatorLeftCurrent() {
    return ElevatorLeftMotor.getSupplyCurrent().getValueAsDouble();
  }

  public double detectElevatorRightCurrent(){
    return ElevatorRightMotor.getSupplyCurrent().getValueAsDouble();
  }

  public void ResetEncoder() {
    ElevatorLeftMotor.setPosition(0);
    ElevatorRightMotor.setPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // if (reset){
    //   if (detectElevatorLeftCurrent() >= Constants.ELEVATOR_RESET_VOLTAGE && detectElevatorRightCurrent() >= Constants.ELEVATOR_RESET_VOLTAGE){
    //     ElevatorLeftMotor.setPosition(0);
    //     ElevatorRightMotor.setPosition(0);
    //     reset = false;
    //   }
    //   else{
    //     ElevatorLeftMotor.set(-0.05);
    //     ElevatorRightMotor.set(0.05);
    //   }
    // }
    SmartDashboard.putNumber("LeftElevatorEncoder", getRightElevatorPosition());
    SmartDashboard.putNumber("RightElevatorEncoder", getLeftElevatorPosition());
  }
}
