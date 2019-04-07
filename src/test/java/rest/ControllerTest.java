package rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import solar.rest.controller.WeatherController;
import solar.rest.model.Weather;
import solar.rest.repository.WeatherRepository;
import solar.rest.service.WeatherServiceImpl;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ControllerTest.class)
public class ControllerTest {
    private MockMvc mockMvc;

    @Mock
    private WeatherServiceImpl weatherService;

    @Mock
    private WeatherRepository weatherRepository;

    @InjectMocks
    private WeatherController weatherController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(weatherController)
                .build();
    }

    @Test
    public void shouldReturnWeatherDay566_whenGetWeatherIsCalled() throws Exception {
        Weather weather = new Weather(566, "Rain/Lluvia");

        when(weatherService.getWeatherByDay(566)).thenReturn(weather);

        mockMvc.perform(get("/clima?day=566"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.day", is(566)))
                .andExpect(jsonPath("$.weather", is("Rain/Lluvia")));
        verify(weatherService, times(1)).getWeatherByDay(566);
        verifyNoMoreInteractions(weatherService);
    }

    @Test
    public void shouldReturnWeatherDay0_whenGetWeatherIsCalled() throws Exception {
        Weather weather = new Weather(0, "Drought/Sequia");

        when(weatherService.getWeatherByDay(0)).thenReturn(weather);

        mockMvc.perform(get("/clima?day=0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.day", is(0)))
                .andExpect(jsonPath("$.weather", is("Drought/Sequia")));

        verify(weatherService, times(1)).getWeatherByDay(0);
        verifyNoMoreInteractions(weatherService);
    }

    @Test
    public void shouldReturnWeatherDay1_whenGetWeatherIsCalled() throws Exception {
        Weather weather = new Weather(1, "Normal weather/ Clima normal");

        when(weatherService.getWeatherByDay(1)).thenReturn(weather);

        mockMvc.perform(get("/clima?day=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.day", is(1)))
                .andExpect(jsonPath("$.weather", is("Normal weather/ Clima normal")));

        verify(weatherService, times(1)).getWeatherByDay(1);
        verifyNoMoreInteractions(weatherService);
    }

    @Test
    public void shouldReturnWeatherDayNotFound_whenGetWeatherIsCalled() throws Exception {

        when(weatherService.getWeatherByDay(-1)).thenThrow(NoSuchElementException.class);
        mockMvc.perform(get("/clima?day=-1"))
                .andExpect(status().isNotFound()) ;
    }

    @Test
    public void shouldReturn500Error_whenGetWeatherIsCalled() throws Exception {

        when(weatherService.getWeatherByDay(-1)).thenThrow(RuntimeException.class);
        mockMvc.perform(get("/clima?day=habrabara"))
                .andExpect(status().isInternalServerError()) ;
    }
}
