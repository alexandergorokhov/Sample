package solar.utils;

import solar.pojo.Point;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {

    public static double fromDegreesToRadiants(double angleRadiant) {
        double result = Math.toRadians(angleRadiant);
        return result;

    }

    public static Point fromPolarToCartesians(double radius, double polarAngle) {
        return new Point(round(
                radius * Math.cos(polarAngle), 2),
                round(radius * Math.sin(polarAngle), 2)
        );
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static boolean arePointsAreColinear(double x1, double y1, double x2, double y2, double x3, double y3) {
        return (y3 - y2) * (x2 - x1) == (y2 - y1) * (x3 - x2);
    }

    // Same expresion based on vector
    //(y3 - y2) * (x2 - x1) == (y2 - y1) * (x3 - x2)
    public static boolean arePointsAreColinear(Point[] vectors) {

        return (vectors[2].getY() - vectors[1].getY()) * (vectors[1].getX() - vectors[0].getX())
                ==
                (vectors[1].getY() - vectors[0].getY()) * (vectors[2].getX() - vectors[1].getX());
    }

    public static boolean arePointsCanBuildATriangle(Point[] points) {
        double side1 = calculateDistanceBetweenTwoPoints(points[0], points[1]);
        double side2 = calculateDistanceBetweenTwoPoints(points[1], points[2]);
        double side3 = calculateDistanceBetweenTwoPoints(points[2], points[0]);
        boolean result = (((side1 + side2) > side3 && (side1 + side3) > side2 && (side2 + side3) > side1));
        return result;
    }

    public static double calculateDistanceBetweenTwoPoints(Point point1, Point point2) {
        double distance = Math.sqrt((point2.getX() - point1.getX()) * (point2.getX() - point1.getX())
                + (point2.getY() - point1.getY()) * (point2.getY() - point1.getY()));
        return distance;
    }

    public static double getPerimeter(Point[] points) {
        double side1 = calculateDistanceBetweenTwoPoints(points[0], points[1]);
        double side2 = calculateDistanceBetweenTwoPoints(points[1], points[2]);
        double side3 = calculateDistanceBetweenTwoPoints(points[2], points[0]);
        return side1 + side2 + side3;
    }


}
