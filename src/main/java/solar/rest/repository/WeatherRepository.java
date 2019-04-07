package solar.rest.repository;

import org.springframework.data.repository.CrudRepository;
import solar.rest.model.Weather;

public interface WeatherRepository extends CrudRepository<Weather, Integer> {

}
