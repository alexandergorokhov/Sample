package solar.position.planet.analyzer;

import solar.pojo.Point;

import java.util.HashMap;

public interface PositionPlanetAnalyzer {
    boolean isPositionConditionSatisfied(Point[] points);

    boolean isPositionIncludesTheSun(Point[] points);
    void weatherFiller(HashMap<String, Integer> weatherMap, Point[] points, int day);
}
