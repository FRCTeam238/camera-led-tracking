import org.opencv.core.Scalar;

class Configuration
{
    public static final int CAMERA_RESOLUTION_WIDTH = 640;
    public static final int CAMERA_RESOLUTION_HEIGHT = 480;

    public static final double CAMERA_FOCAL_LENGTH = 658.0;

    // this port is used to display raw camera output
    public static final int CAMERA_STREAM_PORT = 1185;

    // this port is used to display targeting information
    public static final int TARGET_STREAM_PORT = 1186;

    public static final Scalar COLOR_ALL_HULLS = new Scalar(255, 128, 128);
    public static final Scalar COLOR_TARGET = new Scalar(0, 255, 255);
    public static final Scalar COLOR_TEXT = new Scalar(255, 255, 255);

    public static void Initialize()
    {
    }
}

