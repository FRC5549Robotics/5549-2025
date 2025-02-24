// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import org.photonvision.PhotonCamera;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Limelight extends SubsystemBase {
  /** Creates a new Limelight. */
  PhotonCamera camera;
  DrivetrainSubsystem m_drivetrain;
  PIDController controller = new PIDController(0, 0, 0);

  public Limelight(DrivetrainSubsystem drivetrain) {
    camera = new PhotonCamera("photonvision");
    m_drivetrain = drivetrain;
  }

  public void turnToTarget() {
    var results = camera.getAllUnreadResults();
    var result = results.get(results.size() - 1);
    if (result.hasTargets()) {
      // At least one AprilTag was seen by the camera
      for (var target : result.getTargets()) {
        var targetYaw = target.getYaw();
        m_drivetrain.drive(new ChassisSpeeds(0, 0, controller.calculate(targetYaw)), false);
      }
    }

  // public void ttt2() {}
  // }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
