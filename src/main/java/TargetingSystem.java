import edu.wpi.cscore.VideoSource;
import edu.wpi.cscore.VideoCamera;

public class TargetingSystem
{
    private VideoCamera mCamera = null;
    private TargetTracking mTracking = null;

    public void SetCamera(VideoCamera camera)
    {
        mCamera = camera;
    }

    public void Initialize()
    {
        mCamera.getProperty("gain").set(14);
        mCamera.setBrightness(0);
        mCamera.setExposureManual(2);

        mTracking = new TargetTracking();
        mTracking.Initialize(mCamera);
    }

    public void Run()
    {
        while (true)
        {
            mTracking.Process();
        }
    }
}

