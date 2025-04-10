// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

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
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


public class Limelight extends SubsystemBase {
  /** Creates a new Limelight. */
  PhotonCamera camera;
  DrivetrainSubsystem m_drivetrain;
  CommandXboxController xbox_controller;
  PIDController controller = new PIDController(0, 0, 0);

  public Limelight(DrivetrainSubsystem drivetrain, CommandXboxController xcontroller) {
    camera = new PhotonCamera("photonvision");
    m_drivetrain = drivetrain;
    xbox_controller = xcontroller;

  }

  public void turnToTarget() {
    // var results = camera.getAllUnreadResults();
    // var result = results.get(results.size() - 1);
    // AprilTagFieldLayout aprilTagFieldLayout = AprilTagFieldLayout.loadField(AprilTagFields.kDefaultField);
    
    // var result = camera.getLatestResult();
    // if (result.hasTargets()) {
    //   // At least one AprilTag was seen by the camera
    //   for (var target : result.getTargets()) {
    //     //Alt approach: Gets robot and april tag 3D poses to calculate angle
    //     Transform3d robotToCam = new Transform3d(new Translation3d(0, 0.0, 0), new Rotation3d(0,0,0));
    //     Pose3d robotPose = PhotonUtils.estimateFieldToRobotAprilTag(target.getBestCameraToTarget(), aprilTagFieldLayout.getTagPose(target.getFiducialId()).get(), robotToCam);
    //     double thetaR = robotPose.getRotation().getZ();
    //     Pose3d tagPose = aprilTagFieldLayout.getTagPose(target.getFiducialId()).get();
    //     double thetaA = tagPose.getRotation().getZ();
    //     double thetaT = 0;
    //     if (thetaA < 0) {
    //       thetaT = Math.PI+thetaA;
    //     }
    //     if (thetaA == 0) {
    //       thetaT = (Math.PI-thetaA);
    //     }
    //     else {
    //       thetaT = thetaA-Math.PI;
    //     }

    //     double[] controllerVals = {xbox_controller.getLeftY(), xbox_controller.getLeftX()};


    //     m_drivetrian.generateSpeeds()
    //     m_drivetrain.drive(new ChassisSpeeds(0, 0, controller.calculate(thetaR, thetaT)), false);
        

    //     // double distanceToTarget = PhotonUtils.getDistanceToPose(robotPose, targetPose);
    //     // Translation2d trans = PhotonUtils.estimateCameraToTargetTranslation(0, null);
    //     var targetYaw = target.getYaw();
    //     m_drivetrain.drive(new ChassisSpeeds(0, 0, controller.calculate(targetYaw)), false);
    //   }
    // }

  // public void ttt2() {}
  // }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
