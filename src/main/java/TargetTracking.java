import java.util.ArrayList;

import edu.wpi.first.wpilibj.networktables.*;
import edu.wpi.first.wpilibj.tables.*;
import edu.wpi.cscore.*;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Point;
import org.opencv.core.Core;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.*;
import org.opencv.imgproc.*;

class TargetTracking
{
    private CvSink mImageSink = null;
    private CvSource mImageSource = null;

    private Mat mInputImage = null;

    private GripPipeline mPipeline = null;

    private TargetDetection mTargetDetection = null;
    private TargetCalculation mTargetCalculation = null;

    public void Initialize(VideoCamera camera)
    {
        if (camera == null)
        {
            throw new IllegalArgumentException("Parameter camera is null");
        }

        VideoMode mode = camera.getVideoMode();

        mImageSink = new CvSink("Target Camera");
        mImageSink.setSource(camera);

        System.out.println(
                String.format("TR: TargetTracking.Initialize: res=%dx%d fps=%d",
                    mode.width, mode.height, mode.fps));

        // this source is used to carry the camera image after transformations
        // to detect the targets
        mImageSource = new CvSource("Target Image", 
                mode.pixelFormat, mode.width, mode.height, mode.fps);

        MjpegServer cvStream = new MjpegServer("Target Stream", 
                Configuration.TARGET_STREAM_PORT);
        cvStream.setSource(mImageSource);

        mPipeline = new GripPipeline();

        mInputImage = new Mat();

        mTargetDetection = new TargetDetection();
        mTargetCalculation = new TargetCalculation();
    }

    public void Process()
    {
        long frameTime = mImageSink.grabFrame(mInputImage);
        if (frameTime != 0)
        {
            Mat outputImage = mInputImage;

            mPipeline.process(mInputImage);

            Target target = mTargetDetection.Process(mPipeline.convexHullsOutput(),
                    outputImage);

            if ((target != null) && target.HasData())
            {
                DrawingUtil.DrawTarget(outputImage, target, Configuration.COLOR_TARGET);
                mTargetCalculation.Process(target, outputImage);
            }

            mImageSource.putFrame(outputImage);
        }
    }
}

