import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import solar.pojo.Planet;

public class PlanetTest {
    private Planet planet;

    @Before
    public void setup() {
        planet = new Planet(1,500,"Aruba");
    }

    @Test
    public void planetOrbitPositionAdvanceMore361ClockwiseTest() {
        int result = planet.getPositionAtOrbitInDegreesAtDay(361);
        Assert.assertEquals(1,result);
    }

    @Test
    public void planetOrbitPositionAdvanceMore566ClockwiseTest() {
        int result = planet.getPositionAtOrbitInDegreesAtDay(566);
        Assert.assertEquals(206,result);
    }

    @Test
    public void planetOrbitPositionAdvanceLessThanOneRoundClockwiseTest() {
        int result = planet.getPositionAtOrbitInDegreesAtDay(96);
        Assert.assertEquals(96,result);
    }

    @Test
    public void planetOrbitPositionAdvanceMore361ThanOneRoundConterClockwiseTest() {
        int result = planet.getPositionAtOrbitInDegreesAtDay(-361);
        Assert.assertEquals(359,result);
    }

    @Test
    public void planetOrbitPositionAdvanceLessThanOneRoundConterClockwiseTest() {
        int result = planet.getPositionAtOrbitInDegreesAtDay(-96);
        Assert.assertEquals(264,result);
    }

    @Test
    public void planetOrbitPositionAdvanceMore566ThanOneRoundConterClockwiseTest() {
        int result = planet.getPositionAtOrbitInDegreesAtDay(-566);
        Assert.assertEquals(154,result);
    }
}
