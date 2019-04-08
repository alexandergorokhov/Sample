package solar.position.planet.analyzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import solar.pojo.Point;
import solar.rest.model.Weather;
import solar.rest.service.WeatherService;

import java.util.Arrays;
import java.util.HashMap;

@Component
public class CoolinearPlanetAnalizer implements PositionPlanetAnalyzer {
    @Autowired
    WeatherService weatherService;

    public void weatherFiller(HashMap<String, Integer> weatherMap, Point[] points, int day) {
        if (isPositionConditionSatisfied(points)) {
            if (isPositionIncludesTheSun(points)) {
                weatherService.saveOrUpdate(new Weather(day, "Drought/Sequia"));
                Integer drought = weatherMap.get("Drought");
                weatherMap.put("Drought", ++drought);

            } else {
                weatherService.saveOrUpdate(new Weather(day, "Optimal Condition/Condicion Optima"));
                Integer optimalCondition = weatherMap.get("Optimal Condition");
                weatherMap.put("Optimal Condition", ++optimalCondition);

            }
        }
    }

    @Override
    public boolean isPositionConditionSatisfied(Point[] points) {
        //(y3 - y2) * (x2 - x1) == (y2 - y1) * (x3 - x2)
        return arePointsAreAlligned(points);
    }

    @Override
    public boolean isPositionIncludesTheSun(Point[] points) {
        Point[] pointsIncludingCenter = Arrays.copyOf(points, points.length + 1);
        pointsIncludingCenter[pointsIncludingCenter.length - 1] = new Point(0D, 0D);
        return arePointsAreAllignedWithTheCenter(pointsIncludingCenter);
    }

    private boolean arePointsAreAlligned(Point[] points) {
        return (points[2].getY() - points[1].getY()) * (points[1].getX() - points[0].getX())
                ==
                (points[1].getY() - points[0].getY()) * (points[2].getX() - points[1].getX());
    }

    private boolean arePointsAreAllignedWithTheCenter(Point[] points) {
        return ((points[2].getY() - points[1].getY()) * (points[1].getX() - points[0].getX())
                ==
                (points[1].getY() - points[0].getY()) * (points[2].getX() - points[1].getX()))
                &&
                ((points[2].getY() - points[1].getY()) * (points[3].getX() - points[2].getX())
                        ==
                        (points[3].getY() - points[2].getY()) * (points[2].getX() - points[1].getX()));

    }


    @Override
    public String toString() {
        return "CoolinearPlanetAnalizer";
    }

}
