import java.util.List;
import java.util.ListIterator;

import org.opencv.imgproc.Imgproc;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Point;
import org.opencv.core.*;

class DrawingUtil
{
    public static void DrawAllMats(Mat outputImage, List<Target> hulls, Scalar color)
    {
        for (
                ListIterator<Target> it = hulls.listIterator();
                it.hasNext();)

        {
            Target value = it.next();
            DrawTarget(outputImage, value, color);
        }
    }

    public static void DrawTarget(Mat outputImage, Target target, Scalar color)
    {
        Point tl = new Point();
        Point br = new Point();

        tl.x = target.Left();
        tl.y = target.Top();

        br.x = target.Right();
        br.y = target.Bottom();

        Imgproc.rectangle(outputImage, tl, br, color);
    }

    public static void DrawFilledRectangle(Mat outputImage, Point p1, Point p2,
            Scalar color)
    {
        Imgproc.rectangle(outputImage, p1, p2, color, -1);
    }

    public static void DrawText(Mat outputImage, Point bottomLeft,
            String text, Scalar color)
    {
        Imgproc.putText(outputImage,
                text,
                bottomLeft,
                Core.FONT_HERSHEY_PLAIN,
                1.0,
                color);
    }
}

