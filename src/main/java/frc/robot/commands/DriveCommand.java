package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List; 

public class DriveCommand extends Command {

    private double xDot;
    private double yDot;
    private double thetaDot;
    private boolean fieldRelative;
    private ChassisSpeeds chassisSpeeds, chassisPercent;
    private CommandXboxController m_controller;

    // The subsystem the command runs on
    public final 
    DrivetrainSubsystem drivetrain;

    public DriveCommand(DrivetrainSubsystem subsystem, CommandXboxController controller){
        drivetrain = subsystem;
        m_controller = controller;
        addRequirements(drivetrain);
    }
 
    @Override
    public void initialize() {
    }

            
    @Override
    public void execute() {
      double[] controllerVals = {m_controller.getLeftY(), m_controller.getLeftX(), m_controller.getRightX()};
      boolean[] bool_values = {true, true, true};
      List<Double> speeds = DrivetrainSubsystem.generateSpeeds(bool_values, controllerVals);
      xDot = speeds.get(0);
      yDot = speeds.get(1);
      thetaDot = speeds.get(2);
       
      fieldRelative = true;
    
      chassisSpeeds = ChassisSpeeds.fromRobotRelativeSpeeds(xDot*0.3, yDot*0.3, thetaDot*0.3, drivetrain.getHeading());

      // System.out.println(chassisSpeeds);

      
      drivetrain.drive(chassisSpeeds, true);
    }
}