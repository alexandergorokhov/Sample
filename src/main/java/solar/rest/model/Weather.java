package solar.rest.model;

import javax.persistence.*;

@Entity
@Table(name = "weather_history")
public class Weather {

    @Id
    @Column(name = "day")
    private int day;
    @Column(name = "climate")
    private String weather;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getWeather() {
        return weather;
    }

    public Weather(int day, String weather) {
        this.day = day;
        this.weather = weather;
    }

    public Weather() {

    }
}
