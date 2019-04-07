package rest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import solar.rest.model.Weather;
import solar.rest.repository.WeatherRepository;
import solar.rest.service.WeatherServiceImpl;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ServiceTest.class)

public class ServiceTest {


    @Mock
    private WeatherServiceImpl weatherService;

    @Mock
    private WeatherRepository weatherRepository;

      @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
      }


    @Test
    public void shouldReturnWeatherDay566_whenFindByIdIsCalled() throws Exception {
        Weather weather = new Weather(566, "Rain/Lluvia");

        when(weatherRepository.findById(566)).thenReturn(Optional.of(weather));
        Assert.assertEquals(weather.getDay(),weatherRepository.findById(566).get().getDay());
        Assert.assertEquals(weather.getWeather(),weatherRepository.findById(566).get().getWeather());
         weatherService.getWeatherByDay(566);
        verify(weatherRepository,times(2)).findById(566);

    }

    @Test
    public void shouldReturnWeatherDay0_whenFindByIdIsCalled() throws Exception {
        Weather weather = new Weather(0, "Drought/Sequia");

        when(weatherRepository.findById(0)).thenReturn(Optional.of(weather));
        Assert.assertEquals(weather.getDay(),weatherRepository.findById(0).get().getDay());
        Assert.assertEquals(weather.getWeather(),weatherRepository.findById(0).get().getWeather());
        weatherService.getWeatherByDay(0);
        verify(weatherRepository,times(2)).findById(0);

    }

    @Test
    public void shouldReturnWeatherDay1_whenFindByIdIsCalled() throws Exception {
        Weather weather = new Weather(1, "Normal weather/ Clima normal");

        when(weatherRepository.findById(1)).thenReturn(Optional.of(weather));
        Assert.assertEquals(weather.getDay(),weatherRepository.findById(1).get().getDay());
        Assert.assertEquals(weather.getWeather(),weatherRepository.findById(1).get().getWeather());
        weatherService.getWeatherByDay(1);
        verify(weatherRepository,times(2)).findById(1);

    }


}

