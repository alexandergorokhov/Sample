package solar.simulation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import solar.pojo.Planet;
import solar.pojo.Point;
import solar.position.planet.analyzer.CoolinearPlanetAnalizer;
import solar.position.planet.analyzer.PositionPlanetAnalyzer;
import solar.position.planet.analyzer.TrianglePlanetAnalyzer;
import solar.utils.Utils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class Simulation {
    private ArrayList<Planet> planets;
    private Map<String, PositionPlanetAnalyzer> positionPlanetAnalyzers;
    private HashMap<String, Integer> weatherMap;


    @Autowired
    public Simulation(ArrayList<Planet> planets, Map<String, PositionPlanetAnalyzer> positionPlanetAnalyzers,
                      CoolinearPlanetAnalizer coolinearPlanetAnalizer, TrianglePlanetAnalyzer trianglePlanetAnalyzer) {
        this.planets = planets;
        this.positionPlanetAnalyzers = positionPlanetAnalyzers;
        this.addPlanet(new Planet(1, 500, "Ferengi"));
        this.addPlanet(new Planet(3, 2000, "Betasoide"));
        this.addPlanet(new Planet(-5, 1000, "Vulcano"));
        this.addPositionPlanetAnalyzer(coolinearPlanetAnalizer);
        this.addPositionPlanetAnalyzer(trianglePlanetAnalyzer);
        weatherMap = new HashMap();
        weatherMap.put("Drought", 0);
        weatherMap.put("Optimal Condition", 0);
        weatherMap.put("Rain", 0);
        weatherMap.put("Maximum Rain Day", 0);
    }

    @PostConstruct
    public void init() {
        this.runSimulationNoThreads(10);
    }


    public HashMap<String, Integer> getWeatherMap() {
        return weatherMap;
    }

    public void addPlanet(Planet planet) {
        planets.add(planet);
    }

    public void addPositionPlanetAnalyzer(PositionPlanetAnalyzer positionPlanetAnalyzer) {
        positionPlanetAnalyzers.put(positionPlanetAnalyzer.toString(), positionPlanetAnalyzer);
    }


    public void runSimulationNoThreads(int years) {
        int days = years * 365;
        for (int i = 0; i <= days; i++) {
            try {

                Point[] points = Utils.getCartesianCoordenatsVectorOnADay(i,planets);
                positionPlanetAnalyzers.get("CoolinearPlanetAnalizer").weatherFiller(weatherMap, points, i);
                positionPlanetAnalyzers.get("TrianglePlanetAnalyzer").weatherFiller(weatherMap, points, i);
            } catch (IllegalArgumentException e) {

                System.out.println("Exception : " + e.getMessage());
            }
        }

        System.out.println("Simulation ended");

        System.out.println("Results ; Drought: " + weatherMap.get("Drought")
                + " Rain: " + weatherMap.get("Rain")
                + " Maximum Rain Day: " + weatherMap.get("Maximum Rain Day")
                + " Optimal conditions: " + weatherMap.get("Optimal Condition")
        );

    }

    public Point[] getCartesianCoordenatsVectorOnADay(int i) {
        Point[] points = new Point[3];
        for (int j = 0; j < planets.size(); j++) {
            Planet planet = planets.get(j);
            points[j] = Utils.fromPolarToCartesians(planet.getRadiusKm(), Utils.fromDegreesToRadiants(planet.getPositionAtOrbitInDegreesAtDay(i)));
        }
        return points;
    }


}
