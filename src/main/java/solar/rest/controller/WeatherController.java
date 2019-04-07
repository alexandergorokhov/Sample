package solar.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import solar.rest.model.Weather;
import solar.rest.service.WeatherServiceImpl;

import java.util.NoSuchElementException;


@RestController
public class WeatherController {

    @Autowired
    private WeatherServiceImpl weatherServiceImpl;

    @ResponseBody
    @GetMapping(value ="/clima",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Weather> climate(@RequestParam(value = "day") String dayNumber) {
        try {
            return new ResponseEntity(weatherServiceImpl.getWeatherByDay(Integer.parseInt(dayNumber)), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("The day weather is not present ", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity("Error on our side ", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

}
