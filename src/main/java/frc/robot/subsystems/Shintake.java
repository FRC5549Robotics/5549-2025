package frc.robot.subsystems;


import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Shintake extends SubsystemBase{
    //create the two neo motors

   SparkFlex IntakeMotorLeft;
   SparkMax IntakeMotorRight;
   SparkMaxConfig IntakeLeftConfig, IntakeRightConfig;

   public Shintake(){
        IntakeMotorLeft = new SparkFlex(Constants.INTAKE_MOTOR_LEFT, MotorType.kBrushless);
        IntakeMotorRight = new SparkMax(Constants.INTAKE_MOTOR_RIGHT, MotorType.kBrushless);
        IntakeLeftConfig = new SparkMaxConfig();
        IntakeRightConfig = new SparkMaxConfig();
       IntakeMotorLeft.configure(IntakeLeftConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
        IntakeMotorRight.configure(IntakeRightConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);

       }
    public void intake(double speed) {//make dependent on trigger
        IntakeMotorLeft.set(-speed);
        IntakeMotorRight.set(-speed);
        System.out.println("kind of works");
    }
    public void alt_intake() {//make dependent on trigger
        IntakeMotorLeft.set(-0.7);
        IntakeMotorRight.set(-0.7);
        
        System.out.println("kind of works");
    }
    public void shoot() {
        IntakeMotorLeft.set(Constants.INTAKE_OUTTAKE_SPEED);
        IntakeMotorRight.set(Constants.INTAKE_OUTTAKE_SPEED);
      }
    public void off(){
        IntakeMotorLeft.set(0);
        IntakeMotorRight.set(0);
      }
    


}
    

