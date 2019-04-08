import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import solar.pojo.Planet;
import solar.pojo.Point;
import solar.position.planet.analyzer.TrianglePlanetAnalyzer;
import solar.utils.Utils;

import java.util.ArrayList;
import java.util.HashMap;

public class TriangleAnalyzerTest {
    private HashMap<String, Integer> weatherMap;
    private ArrayList<Planet> planets;

    @Before
    public void setup() {
        weatherMap = new HashMap<String, Integer>();
        planets = new ArrayList<>();
        planets.add(new Planet(1, 500, "Ferengi"));
        planets.add(new Planet(3, 2000, "Betasoide"));
        planets.add(new Planet(-5, 1000, "Vulcano"));
    }

    @Test
    public void shouldRetrunTrueIsTriagleValidTest() {
        Point side1 = Utils.fromPolarToCartesians(500, Utils.fromDegreesToRadiants(45));
        Point side2 = Utils.fromPolarToCartesians(1000, Utils.fromDegreesToRadiants(315));
        Point side3 = Utils.fromPolarToCartesians(2000, Utils.fromDegreesToRadiants(180));
        Point[] points = new Point[3];
        points[0] = side1;
        points[1] = side2;
        points[2] = side3;
        TrianglePlanetAnalyzer trianglePlanetAnalyzer = new TrianglePlanetAnalyzer(points);
        boolean result = trianglePlanetAnalyzer.isPositionConditionSatisfied(points);
        Assert.assertEquals(true, result);

    }

    @Test
    public void shouldRetrunTrueIsTriagleValidAndIncludeSunTest() {
        Point side1 = Utils.fromPolarToCartesians(500, Utils.fromDegreesToRadiants(45));
        Point side2 = Utils.fromPolarToCartesians(1000, Utils.fromDegreesToRadiants(315));
        Point side3 = Utils.fromPolarToCartesians(2000, Utils.fromDegreesToRadiants(180));
        Point[] points = new Point[3];
        points[0] = side1;
        points[1] = side2;
        points[2] = side3;
        TrianglePlanetAnalyzer trianglePlanetAnalyzer = new TrianglePlanetAnalyzer(points);
        boolean result = trianglePlanetAnalyzer.isPositionIncludesTheSun(points);
        Assert.assertEquals(true, result);

    }

    @Test
    public void shouldRetrunTrueIsTriagleValidAndNotIncludeSunTest() {
        Point side1 = Utils.fromPolarToCartesians(500, Utils.fromDegreesToRadiants(45));
        Point side2 = Utils.fromPolarToCartesians(1000, Utils.fromDegreesToRadiants(315));
        Point side3 = Utils.fromPolarToCartesians(2000, Utils.fromDegreesToRadiants(320));
        Point[] points = new Point[3];
        points[0] = side1;
        points[1] = side2;
        points[2] = side3;
        TrianglePlanetAnalyzer trianglePlanetAnalyzer = new TrianglePlanetAnalyzer(points);
        boolean result = trianglePlanetAnalyzer.isPositionIncludesTheSun(points);
        Assert.assertEquals(false, result);

    }

    @Test
    public void shouldReturnTrueWillItRainTest() {

        Point[] points = Utils.getCartesianCoordenatsVectorOnADay(566, planets);
        TrianglePlanetAnalyzer trianglePlanetAnalyzer = new TrianglePlanetAnalyzer(points);
        boolean result = trianglePlanetAnalyzer.checkRainning(566, points);
        Assert.assertEquals(true, result);

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionTest() {
        Point[] points = Utils.getCartesianCoordenatsVectorOnADay(90, planets);
        TrianglePlanetAnalyzer trianglePlanetAnalyzer = new TrianglePlanetAnalyzer(points);
        boolean result = trianglePlanetAnalyzer.checkRainning(90, points);

    }

    @Test
    public void shouldComputeMaximumDayPerimeterTest() {

        Point[] points = Utils.getCartesianCoordenatsVectorOnADay(566, planets);
        TrianglePlanetAnalyzer trianglePlanetAnalyzer = new TrianglePlanetAnalyzer(points);
        trianglePlanetAnalyzer.computeMaximumPerimeter(566,points,weatherMap);

        points = Utils.getCartesianCoordenatsVectorOnADay(1, planets);
        trianglePlanetAnalyzer.computeMaximumPerimeter(1,points,weatherMap);

        Assert.assertEquals(566, weatherMap.get("Maximum Rain Day").intValue());

    }

}
