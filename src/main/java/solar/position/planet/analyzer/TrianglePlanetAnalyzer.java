package solar.position.planet.analyzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import solar.pojo.Point;
import solar.pojo.Triangle;
import solar.rest.model.Weather;
import solar.rest.service.WeatherService;
import solar.utils.Utils;

import java.util.HashMap;

@Component
public class TrianglePlanetAnalyzer implements PositionPlanetAnalyzer {

    @Autowired
    WeatherService weatherService;

    private Triangle triangle;
    private double maxPerimeter;


    public TrianglePlanetAnalyzer() {
    }

    public TrianglePlanetAnalyzer(Point[] points) {
        this.triangle = formATriangle(points);
    }

    private Triangle formATriangle(Point[] points) {
        if (Utils.arePointsCanBuildATriangle(points)) {
            return new Triangle(points);
        } else {
            throw new IllegalArgumentException("The points can not form a triangle");
        }
    }


    @Override
    public void weatherFiller(HashMap<String, Integer> weatherMap, Point[] points, int day) {
        if (checkRainning(day, points)) {
            weatherService.saveOrUpdate(new Weather(day, "Rain/Lluvia"));
            Integer rain = weatherMap.get("Rain");
            weatherMap.put("Rain", ++rain);
            computeMaximumPerimeter(day, points, weatherMap);

        } else {
            weatherService.saveOrUpdate(new Weather(day, "Normal weather/ Clima normal"));
        }
    }

    public boolean checkRainning(int i, Point[] points) {

        if (isPositionConditionSatisfied(points)) {

            if (isPositionIncludesTheSun(points)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isPositionConditionSatisfied(Point[] points) {

        triangle = formATriangle(points);
        return true;
    }


    @Override
    public boolean isPositionIncludesTheSun(Point[] points) {

        return triangle.containsPoint(new Point(0D, 0D));
    }

    public void computeMaximumPerimeter(int day, Point[] points, HashMap<String, Integer> weatherMap) {
        double possibleMaxPerimeter = Utils.getPerimeter(points);
        if (possibleMaxPerimeter > maxPerimeter) {
            maxPerimeter = possibleMaxPerimeter;
            weatherMap.put("Maximum Rain Day", day);
        }
    }

    @Override
    public String toString() {
        return "TrianglePlanetAnalyzer";
    }
}
