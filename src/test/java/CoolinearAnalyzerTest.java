import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import solar.pojo.Point;
import solar.position.planet.analyzer.CoolinearPlanetAnalizer;
import solar.utils.Utils;

public class CoolinearAnalyzerTest {
    CoolinearPlanetAnalizer coolinearPlanetAnalizer;

    @Before
    public void setUp() {
        coolinearPlanetAnalizer = new CoolinearPlanetAnalizer();

    }


    @Test
    public void shouldReturnTrueThreeColinearPoints() {
        Point side1 = Utils.fromPolarToCartesians(12, Utils.fromDegreesToRadiants(45));
        Point side2 = Utils.fromPolarToCartesians(3, Utils.fromDegreesToRadiants(45));
        Point side3 = Utils.fromPolarToCartesians(12, Utils.fromDegreesToRadiants(225));
        Point[] points = new Point[3];
        points[0] = side1;
        points[1] = side2;
        points[2] = side3;
        boolean result = coolinearPlanetAnalizer.isPositionConditionSatisfied(points);
        Assert.assertEquals(true, result);
    }

    @Test
    public void shouldReturnTrueThreeColinearPointsIncludeSun() {
        Point side1 = Utils.fromPolarToCartesians(12, Utils.fromDegreesToRadiants(45));
        Point side2 = Utils.fromPolarToCartesians(3, Utils.fromDegreesToRadiants(45));
        Point side3 = Utils.fromPolarToCartesians(12, Utils.fromDegreesToRadiants(225));
        Point[] points = new Point[3];
        points[0] = side1;
        points[1] = side2;
        points[2] = side3;
        boolean result = coolinearPlanetAnalizer.isPositionIncludesTheSun(points);
        Assert.assertEquals(true, result);
    }

    @Test
    public void shouldReturnFalseThreeColinearPointsNotIncludeSun() {
        Point side1 = Utils.fromPolarToCartesians(12, Utils.fromDegreesToRadiants(45));
        Point side2 = Utils.fromPolarToCartesians(3, Utils.fromDegreesToRadiants(315));
        Point side3 = Utils.fromPolarToCartesians(12, Utils.fromDegreesToRadiants(315));
        Point[] points = new Point[3];
        points[0] = side1;
        points[1] = side2;
        points[2] = side3;
        boolean result = coolinearPlanetAnalizer.isPositionIncludesTheSun(points);
        Assert.assertEquals(false, result);
    }

    @Test
    public void shouldReturnFalseThreeColinearPointsNotIncludeSun2() {
        Point side1 = Utils.fromPolarToCartesians(12, Utils.fromDegreesToRadiants(135));
        Point side2 = Utils.fromPolarToCartesians(3, Utils.fromDegreesToRadiants(225));
        Point side3 = Utils.fromPolarToCartesians(12, Utils.fromDegreesToRadiants(225));
        Point[] points = new Point[3];
        points[0] = side1;
        points[1] = side2;
        points[2] = side3;
        boolean result = coolinearPlanetAnalizer.isPositionIncludesTheSun(points);
        Assert.assertEquals(false, result);
    }

    @Test
    public void shouldReturnFalseThreeColinearPoints() {
        Point side1 = Utils.fromPolarToCartesians(12, Utils.fromDegreesToRadiants(45));
        Point side2 = Utils.fromPolarToCartesians(3, Utils.fromDegreesToRadiants(315));
        Point side3 = Utils.fromPolarToCartesians(12, Utils.fromDegreesToRadiants(200));
        Point[] points = new Point[3];
        points[0] = side1;
        points[1] = side2;
        points[2] = side3;
        boolean result = coolinearPlanetAnalizer.isPositionConditionSatisfied(points);
        Assert.assertEquals(false, result);
    }
}
