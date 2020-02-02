package RoboRaiders.hubbot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.List;
@TeleOp
public class DetecctingTheColorRed extends LinearOpMode {

    OpenCvCamera phoneCam;
    StageSwitchingPipeline stageSwitchingPipeline;

    @Override
    public void runOpMode() throws InterruptedException {

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        phoneCam.openCameraDevice();
        stageSwitchingPipeline = new DetectingFoundationRed.StageSwitchingPipeline();
        phoneCam.setPipeline(stageSwitchingPipeline);
        phoneCam.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);

        waitForStart();

        while (opModeIsActive()) {

        }
    }

    public enum ColorPreset {
        RED,
        BLUE,
        YELLOW,
        WHITE
    }

    private ColorPreset color = ColorPreset.RED;
    private double threshold = -1;
    private List<Mat> channels = new ArrayList<>();


    public DetecctingTheColorRed (ColorPreset filterColor) {
         updateSettings(filterColor, -1);
    }
    public DetecctingTheColorRed (ColorPreset filterColor, double filterThreshold) {
        updateSettings(filterColor, filterThreshold);
    }
    private  void updateSettings(ColorPreset filterColor, double filterThreshold) {
        color = filterColor;
        threshold = filterThreshold;
    }
    public void process(Mat input, Mat mask) {
        channels = new ArrayList<>();

        switch(color) {
            case RED:
                if(threshold == -1) {
                    threshold = 164;
                }

                Imgproc.cvtColor(input, input, Imgproc.COLOR_RGB2Lab);
                Imgproc.GaussianBlur(input, input, new Size(3,3),0);
                Core.split(input, channels);
                Imgproc.threshold(channels.get(1), mask, threshold, 255, Imgproc.THRESH_BINARY);
                break;
            case BLUE:
                if (threshold == -1) {
                    threshold = 145;
                }

                Imgproc.cvtColor(input, input, Imgproc.COLOR_RGB2Lab);
                Imgproc.GaussianBlur(input, input, new Size(3,3),0);
                Core.split(input, channels);
                Core.inRange(channels.get(0), new Scalar(threshold, 150, 40), new Scalar(255, 150, 150), mask);
                break;
            case YELLOW:
                if (threshold == -1) {
                    threshold = 70;
                }

                Mat lab = new Mat(input.size(),0);
                Imgproc.cvtColor(input, lab, Imgproc.COLOR_RGB2Lab);
                Mat temp = new Mat();
                Core.inRange(input, new Scalar(0,0,0), new Scalar(255, 255, 164), temp);
                Mat mask2 = new Mat(input.size(),0);
                temp.copyTo(mask2);
                input.copyTo(input, mask2);
                mask2.release();
                temp.release();
                lab.release();
                Imgproc.cvtColor(input, input, Imgproc.COLOR_RGB2Lab);
                Imgproc.GaussianBlur(input,input, new Size(3,3),0);
                Core.split(input, channels);
                if (channels.size() > 0) {
                    Imgproc.threshold(channels.get(1), mask, threshold, 255, Imgproc.THRESH_BINARY_INV);
                }
                break;
            }
            for (int i=0;i<channels.size();i++) {
                channels.get(i).release();
            }
            input.release();
        }


}

