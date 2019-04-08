import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import solar.Merli;
import solar.rest.repository.WeatherRepository;
import solar.rest.service.WeatherServiceImpl;
import solar.simulation.Simulation;

import java.util.HashMap;

@Ignore
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Merli.class, Simulation.class, WeatherServiceImpl.class, WeatherRepository.class})
public class SimulationTest {

    @Autowired
    private Simulation simulation;

    @Test
    public void simulationNoThreadsTest() {
        HashMap<String,Integer> result = simulation.getWeatherMap();
        Assert.assertEquals(Integer.valueOf(41), result.get("Drought"));
        Assert.assertEquals(Integer.valueOf(1167), result.get("Rain"));
        Assert.assertEquals(Integer.valueOf(72), result.get("Maximum Rain Day"));
        Assert.assertEquals(Integer.valueOf(0), result.get("Optimal Condition"));
    }

}
