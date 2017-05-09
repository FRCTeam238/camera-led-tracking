import org.opencv.core.Mat;

import org.opencv.core.Point;

import edu.wpi.first.wpilibj.networktables.NetworkTable;


class TargetCalculation
{
    public void Initialize()
    {
        // do nothing
    }

    public void Process(Target target, Mat outputMarkupImage)
    {
        NetworkTable networkTable = NetworkTable.getTable("SmartDashboard");

        final int screenWidth = outputMarkupImage.width();
        final int screenHeight = outputMarkupImage.height();

        // find the center point of the selected target

        Point center = new Point();
        center = target.GetCenter();

        String centerText = String.format("C: (%f, %f)",
                center.x, center.y);
        DrawingUtil.DrawText(outputMarkupImage,
                new Point(10, screenHeight - 35),
                centerText,
                Configuration.COLOR_TEXT);

        //System.out.println("C: " + center.toString());
        //System.out.println("T: " + screenWidth + "x" + screenHeight);

        DrawingUtil.DrawFilledRectangle(outputMarkupImage,
                new Point(center.x - 2, center.y - 2),
                new Point(center.x + 2, center.y + 2),
                Configuration.COLOR_TARGET);

        // reposition the center point so that it is relative to
        // the center of the screen
        center.x = (screenWidth / 2) - center.x;
        center.y = (screenHeight / 2) - center.y;

        String xytext = String.format("screen=%dx%d   point xy=(%f,%f)",
                screenWidth, screenHeight, center.x, center.y);
        DrawingUtil.DrawText(outputMarkupImage,
                new Point(10, screenHeight - 20),
                xytext,
                Configuration.COLOR_TEXT);

        double horizontalAngle_radians = CalculateTargetAngle(center.x);
        double verticalAngle_radians = CalculateTargetAngle(center.y);

        double horizontalAngle = Math.toDegrees(horizontalAngle_radians);
        double verticalAngle = Math.toDegrees(verticalAngle_radians);

        String text = String.format("angle horz: %f  angle vert: %f",
                    horizontalAngle, verticalAngle);

        DrawingUtil.DrawText(outputMarkupImage,
                new Point(10, screenHeight - 5),
                text,
                Configuration.COLOR_TEXT);
    }

    public static double CalculateTargetAngle(double pixelPosition)
    {
        double thetaV =
            Math.atan(pixelPosition / Configuration.CAMERA_FOCAL_LENGTH);
        return thetaV;
    }
}

