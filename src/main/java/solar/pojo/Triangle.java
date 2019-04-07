package solar.pojo;

import static solar.utils.Utils.calculateDistanceBetweenTwoPoints;

public class Triangle {
    private final double x3, y3;
    private final double y23, x32, y31, x13;
    private final double det, minD, maxD;
    private final double side1, side2, side3;

    public Triangle(Point[] points) {
        side1 = calculateDistanceBetweenTwoPoints(points[0], points[1]);
        side2 = calculateDistanceBetweenTwoPoints(points[1], points[2]);
        side3 = calculateDistanceBetweenTwoPoints(points[2], points[0]);
        this.x3 = points[2].getX();
        this.y3 = points[2].getY();
        y23 = points[1].getY() - y3;
        x32 = x3 - points[1].getX();
        y31 = y3 - points[0].getY();
        x13 = points[0].getX() - x3;
        det = y23 * x13 - x32 * y31;
        minD = Math.min(det, 0);
        maxD = Math.max(det, 0);
    }

    //If point belongs using triangle determinant formula
    public boolean containsPoint(Point point) {
        double dx = point.getX() - x3;
        double dy = point.getY() - y3;
        double a = y23 * dx + x32 * dy;
        if (a < minD || a > maxD)
            return false;
        double b = y31 * dx + x13 * dy;
        if (b < minD || b > maxD)
            return false;
        double c = det - a - b;
        if (c < minD || c > maxD)
            return false;
        return true;
    }

    public double getPerimeter() {
        return side1 + side2 + side3;
    }

}
