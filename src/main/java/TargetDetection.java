import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Point;

class TargetDetection
{
    public Target Process(ArrayList<MatOfPoint> hullOutput, Mat outputMarkupImage)
    {
        List<Target> points = ConvertHullsToTarget(hullOutput);

        DrawingUtil.DrawAllMats(outputMarkupImage, points,
                Configuration.COLOR_ALL_HULLS);

        Target target = FindLargestTarget(points);

        if (target.HasData())
        {
            return target;
        }
        else
        {
            return null;
        }
    }

    public static Target FindLargestTarget(List<Target> possibleTargets)
    {
        Target target = new Target();

        for (ListIterator<Target> it = possibleTargets.listIterator();
                it.hasNext();
        )
        {
            Target compare = it.next();

            if (target.Area() < compare.Area())
            {
                target = compare;
            }
        }

        return target;
    }

    public static List<Target> ConvertHullsToTarget(List<MatOfPoint> hulls)
    {
        ArrayList<Target> targetList = new ArrayList<Target>();

        for (ListIterator<MatOfPoint> it = hulls.listIterator();
                it.hasNext();
        )
        {
            MatOfPoint it_data = it.next();
            Target it_target = new Target(it_data);
            targetList.add(it_target);
        }

        return targetList;
    }
}

