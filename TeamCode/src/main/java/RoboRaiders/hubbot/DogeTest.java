//package RoboRaiders.hubbot;
//
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//
//import org.openftc.easyopencv.OpenCvCameraRotation;
//import org.openftc.easyopencv.OpenCvInternalCamera;
//
//import RoboRaiders.Logger.Logger;
//import easyopencv.examples.InternalCameraExample;
//import easyopencv.examples.WebcamExample;
//
//
//@TeleOp(name="Gold Example", group="DogeCV")
//@Disabled
//
//public class DogeTest extends LinearOpMode
//{
//    // Detector object
//    private MySkystoneDetector detector;
//    private OpenCvInternalCamera phoneCam;
//
//    public void runOpMode()
//    {
//        Logger L = new Logger(String.valueOf("TEST"));
//        /**
//         * NOTE: Many comments have been omitted from this sample for the
//         * sake of conciseness. If you're just starting out with EasyOpenCv,
//         * you should take a look at {@link InternalCameraExample} or its
//         * webcam counterpart, {@link WebcamExample} first.
//         */
//
//
//        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
//        phoneCam = new OpenCvInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
//        phoneCam.openCameraDevice();
//        detector = new MySkystoneDetector();
//        detector.useDefaults();
//        phoneCam.setPipeline(detector);
//        phoneCam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
//
//        waitForStart();
//
//        while (opModeIsActive())
//        {
//            telemetry.addData("Num contours found", detector.getScreenPosition());
//            telemetry.addData("rectangle", detector.foundRectangle());
//            telemetry.addData("score", detector.getBestScore());
//            telemetry.update();
//            sleep(100);
//        }
//    }
//
//}
