import org.junit.Assert;
import org.junit.Test;
import solar.pojo.Triangle;
import solar.pojo.Point;
import solar.utils.Utils;

public class UtilsTest {

    @Test
    public void radTograd195Test() {
        Point result = Utils.fromPolarToCartesians(12, Utils.fromDegreesToRadiants(195));
        Assert.assertEquals(-11.59D, result.getX(), 0.01);
        Assert.assertEquals(-3.105D, result.getY(), 0.01);
    }

    @Test
    public void radTograd90Test() {
        Point result = Utils.fromPolarToCartesians(12, Utils.fromDegreesToRadiants(90));
        Assert.assertEquals(0D, result.getX(), 0.01);
        Assert.assertEquals(12, result.getY(), 0.01);
    }

    @Test
    public void radTograd270Test() {
        Point result = Utils.fromPolarToCartesians(12, Utils.fromDegreesToRadiants(270));
        Assert.assertEquals(0D, result.getX(), 0.01);
        Assert.assertEquals(-12D, result.getY(), 0.01);
    }

    @Test
    public void itShouldReturnTrueColinearTest() {
        Point side1 = Utils.fromPolarToCartesians(12, Utils.fromDegreesToRadiants(45));
        Point side2 = Utils.fromPolarToCartesians(0, Utils.fromDegreesToRadiants(0));
        Point side3 = Utils.fromPolarToCartesians(12, Utils.fromDegreesToRadiants(225));
        boolean result = Utils.arePointsAreColinear(side1.getX(), side1.getY(), side2.getX(), side2.getY(), side3.getX(), side3.getY());
        Assert.assertEquals(true, result);

    }

    @Test
    public void itShouldReturnTrueColinearVectorBasedTest() {
        Point side1 = Utils.fromPolarToCartesians(12, Utils.fromDegreesToRadiants(45));
        Point side2 = Utils.fromPolarToCartesians(0, Utils.fromDegreesToRadiants(0));
        Point side3 = Utils.fromPolarToCartesians(12, Utils.fromDegreesToRadiants(225));
        Point[] points = new Point[3];
        points[0] = side1;
        points[1] = side2;
        points[2] = side3;
        boolean result = Utils.arePointsAreColinear(points);
        Assert.assertEquals(true, result);

    }

    @Test
    public void shouldRetrunTrueIsTriagleValidTest() {

        Point side1 = Utils.fromPolarToCartesians(500, Utils.fromDegreesToRadiants(45));
        Point side2 = Utils.fromPolarToCartesians(1000, Utils.fromDegreesToRadiants(0));
        Point side3 = Utils.fromPolarToCartesians(2000, Utils.fromDegreesToRadiants(225));
        Point[] points = new Point[3];
        points[0] = side1;
        points[1] = side2;
        points[2] = side3;
        boolean result = Utils.arePointsCanBuildATriangle(points);
        Assert.assertEquals(true, result);

    }

}
