package solar.rest.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import solar.rest.model.Weather;
import solar.rest.repository.WeatherRepository;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;


    @Override
    public Weather getWeatherByDay(Integer day) {

        return weatherRepository.findById(day).get();
    }

    @Override
    public void saveOrUpdate(Weather weather) {
        try {
            weatherRepository.save(weather);
        }catch (Exception e){
            System.out.println("Exception occured while saving the data");
        }

    }
}


