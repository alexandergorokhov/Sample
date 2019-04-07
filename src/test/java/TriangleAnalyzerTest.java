import org.junit.Assert;
import org.junit.Test;
import solar.pojo.Point;
import solar.position.planet.analyzer.TrianglePlanetAnalyzer;
import solar.utils.Utils;

public class TriangleAnalyzerTest {
    @Test
    public void shouldRetrunTrueIsTriagleValidTest(){
        Point side1 = Utils.fromPolarToCartesians(500,Utils.fromDegreesToRadiants(45));
        Point side2 = Utils.fromPolarToCartesians(1000,Utils.fromDegreesToRadiants(315));
        Point side3 = Utils.fromPolarToCartesians(2000,Utils.fromDegreesToRadiants(180));
        Point[] points = new Point[3];
        points[0] = side1;
        points[1] = side2;
        points[2] = side3;
        TrianglePlanetAnalyzer trianglePlanetAnalyzer = new TrianglePlanetAnalyzer(points);
        boolean result = trianglePlanetAnalyzer.isPositionConditionSatisfied(points);
        Assert.assertEquals(true,result);

    }

    @Test
    public void shouldRetrunTrueIsTriagleValidAndIncludeSunTest(){
        Point side1 = Utils.fromPolarToCartesians(500,Utils.fromDegreesToRadiants(45));
        Point side2 = Utils.fromPolarToCartesians(1000,Utils.fromDegreesToRadiants(315));
        Point side3 = Utils.fromPolarToCartesians(2000,Utils.fromDegreesToRadiants(180));
        Point[] points = new Point[3];
        points[0] = side1;
        points[1] = side2;
        points[2] = side3;
        TrianglePlanetAnalyzer trianglePlanetAnalyzer = new TrianglePlanetAnalyzer(points);
        boolean result = trianglePlanetAnalyzer.isPositionIncludesTheSun(points);
        Assert.assertEquals(true,result);

    }

    @Test
    public void shouldRetrunTrueIsTriagleValidAndNotIncludeSunTest(){
        Point side1 = Utils.fromPolarToCartesians(500,Utils.fromDegreesToRadiants(45));
        Point side2 = Utils.fromPolarToCartesians(1000,Utils.fromDegreesToRadiants(315));
        Point side3 = Utils.fromPolarToCartesians(2000,Utils.fromDegreesToRadiants(320));
        Point[] points = new Point[3];
        points[0] = side1;
        points[1] = side2;
        points[2] = side3;
        TrianglePlanetAnalyzer trianglePlanetAnalyzer = new TrianglePlanetAnalyzer(points);
        boolean result = trianglePlanetAnalyzer.isPositionIncludesTheSun(points);
        Assert.assertEquals(false,result);

    }

}
