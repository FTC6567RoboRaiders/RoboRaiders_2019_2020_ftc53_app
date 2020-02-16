package RoboRaiders.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
//import org.openftc.easyopencv.OpenCvWebcam;

import RoboRaiders.Autonomous.RoboRaidersPipelineWebcam;

@TeleOp
public class WebcamDetection extends LinearOpMode {
    public static final String TAG = "Vuforia Navigation Sample";
    int pattern = 999;

    OpenCvCamera webcam;
   // BrightnessDetection.SamplePipeline stone_pipeline;
    RoboRaidersPipelineWebcam stone_pipeline;
    public void runOpMode() {
        float leftRec[] = {9f, 5f,15f,15f};
        float rightRec[] = {9f, 17f, 15f, 27f};

       double startTime;



        // red side
        leftRec[0] = 9f;
        leftRec[1] = 5f;
        leftRec[2] = 15f;
        leftRec[3] = 15f;

        rightRec[0] = 9f;
        rightRec[1] = 15f;
        rightRec[2] = 15f;
        rightRec[3] = 25f;

//
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
////        webcam = new OpenCvWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        webcam.openCameraDevice();
        stone_pipeline = new RoboRaidersPipelineWebcam(pattern,leftRec,rightRec);
        webcam.setPipeline(stone_pipeline);

        webcam.startStreaming(320, 240, OpenCvCameraRotation.SIDEWAYS_RIGHT);

        waitForStart();
//
//        startTime = System.currentTimeMillis();
//        while (opModeIsActive() && (System.currentTimeMillis() - startTime) < 5000) {
//            super.updateTelemetry(telemetry);
//            telemetry.addData("PATTERN", stone_pipeline.getPattern());
//        }
//
//        webcam.stopStreaming();


        // blue side
        leftRec[0] = 10f;
        leftRec[1] = 3f;
        leftRec[2] = 15f;
        leftRec[3] = 12f;

        rightRec[0] = 10f;
        rightRec[1] = 13f;
        rightRec[2] = 15f;
        rightRec[3] = 22f;

        pattern = 999;

        webcam.openCameraDevice();
        stone_pipeline = new RoboRaidersPipelineWebcam(pattern,leftRec,rightRec,this);
        webcam.setPipeline(stone_pipeline);
        webcam.startStreaming(320,240, OpenCvCameraRotation.SIDEWAYS_RIGHT);

        startTime = System.currentTimeMillis();
        while (opModeIsActive() && (System.currentTimeMillis() - startTime) < 200000) {
            super.updateTelemetry(telemetry);
            telemetry.addData("PATTERN", stone_pipeline.getPattern());
            telemetry.addData("right brightness: ", stone_pipeline.getRight_br());
            telemetry.addData("left brightness: ",stone_pipeline.getLeft_br());
        }

        webcam.stopStreaming();




        webcam.closeCameraDevice();   // The last thing done

    }
}