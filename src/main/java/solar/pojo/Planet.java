package solar.pojo;

public class Planet {

    private int angularVelocity;
    private int radiusKm;
    private String name;

    public Planet(int angularVelocity, int radiusKm, String name) {
        this.angularVelocity = angularVelocity;
        this.radiusKm = radiusKm;
        this.name = name;
    }

    public int getRadiusKm() {
        return radiusKm;
    }

    public int getPositionAtOrbitInDegreesAtDay(int day) {
        int result = angularVelocity * day;
        if (result > 360) {
            result = result - (result / 360) * 360;
        }
        if (result < -360) {
            result = (result - (result / 360) * 360);
        }
        if (result < 0) {
            result = result + 360;
        }
        return result;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "angularVelocity=" + angularVelocity +
                ", radiusKm=" + radiusKm +
                ", name='" + name + '\'' +
                '}';
    }

}
