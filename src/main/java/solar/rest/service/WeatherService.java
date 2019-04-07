package solar.rest.service;


import solar.rest.model.Weather;

public interface WeatherService {
    public Weather getWeatherByDay(Integer day);

    public void saveOrUpdate(Weather weather);
}
