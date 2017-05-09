import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

class Target
{
    private MatOfPoint mHull = null;
    private Rect mBounds = null;

    public Target()
    {
        SetData(null);
    }

    public Target(MatOfPoint hull)
    {
        SetData(hull);
    }

    public void SetData(MatOfPoint hull)
    {
        if (hull == null)
        {
            mHull = null;
            mBounds = null;
        }
        else
        {
            mHull = hull;
            mBounds = Imgproc.boundingRect(mHull);
        }
    }

    public MatOfPoint GetData()
    {
        return mHull;
    }

    public boolean HasData()
    {
        return GetData() != null;
    }

    public Rect GetBoundingRectangle()
    {
        return mBounds;
    }

    public int GetWidth()
    {
        int retval = 0;

        if (mBounds != null)
        {
            retval = mBounds.width;
        }

        return retval;
    }

    public int GetHeight()
    {
        int retval = 0;

        if (mBounds != null)
        {
            retval = mBounds.height;
        }

        return retval;
    }

    public int Top()
    {
        int retval = 0;
        retval = mBounds.y;
        return retval;
    }

    public int Bottom()
    {
        int retval = 0;
        retval = mBounds.y + mBounds.height;
        return retval;
    }

    public int Left()
    {
        int retval = 0;
        retval = mBounds.x;
        return retval;
    }

    public int Right()
    {
        int retval = 0;
        retval = mBounds.x + mBounds.width;
        return retval;
    }

    public int Area()
    {
        return GetWidth() * GetHeight();
    }

    public Point GetCenter()
    {
        Point retval = new Point();

        retval.x = mBounds.x + (mBounds.width / 2);
        retval.y = mBounds.y + (mBounds.height / 2);

        return retval;
    }
}

