// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.LimelightHelpers;


public class Limelight extends SubsystemBase {
  /** Creates a new Limelight. */
  PhotonCamera camera;
  DrivetrainSubsystem m_drivetrain;
  CommandXboxController xbox_controller;
  PIDController controller = new PIDController(0.1, 0, 0);
  private double thetaDot;
  NetworkTable limelightTable;

  public Limelight(DrivetrainSubsystem drivetrain, CommandXboxController xcontroller) {
    m_drivetrain = drivetrain;
    xbox_controller = xcontroller;
    limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
  }

  public double turnToTarget() {
    if (xbox_controller.getHID().getBButton()) {
    double[] s = LimelightHelpers.getBotPose_TargetSpace("limelight");
    double angle = s[4];
    System.out.println(angle);
    return controller.calculate(angle, 0);
    }
    return 0.0;
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // double[] s = LimelightHelpers.getBotPose_TargetSpace("limelight");
    // System.out.println(s);
    // var s = NetworkTableInstance.getDefault().getTable("limelight").getEntry("botpose_targetspace").getDoubleArray(new double[6]);
    // System.out.println(s);

    // for (int i = 0; i<s.length; i++) {
    //   System.out.println(s[i]);
    // }
    // System.out.println(s[4]);
  }}

