package com.weatherApp.weatherApp.Services.Impl;
import com.weatherApp.weatherApp.Entities.Coord;
import com.weatherApp.weatherApp.Entities.Main;
import com.weatherApp.weatherApp.Entities.WeatherInformation;
import com.weatherApp.weatherApp.Exceptions.CoordinatesNotFoundException;
import com.weatherApp.weatherApp.Exceptions.WeatherDataNotFoundException;
import com.weatherApp.weatherApp.Services.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service

public class WeatherServiceImpl implements WeatherService {
    @Value("${weather.api.key}")
    private String weatherApiKey;

    WeatherInformation weatherInformation;

    @Override
    public WeatherInformation getWeatherInfos(Double lat, Double lon) {
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=%s",lat,lon,weatherApiKey);
        RestTemplate restTemplate = new RestTemplate();
        try{
            weatherInformation=restTemplate.getForObject(url, WeatherInformation.class);
            if(weatherInformation == null){
                throw new WeatherDataNotFoundException("Weather data is currently unavailable.");
            }
        } catch (Exception e) {
            throw new WeatherDataNotFoundException("Error fetching weather data: " + e.getMessage());
        }
        return weatherInformation;
    }

    @Override
    public Main getMain(Double lat, Double lon) {
        if(weatherInformation != null){
            return weatherInformation.getMain();
        } else{
            WeatherInformation data = getWeatherInfos(lat,lon);
            if (data == null) {
                throw new WeatherDataNotFoundException("Weather data is currently unavailable.");
            }
            return data.getMain();
        }
    }

    @Override
    public Coord getCoordinates(Double lat, Double lon) {
        //return weatherInformation!=null? weatherInformation.getCoord(): getWeatherInfos(lat,lon).getCoord();
        if (weatherInformation != null) {
            return weatherInformation.getCoord();
        } else {
            WeatherInformation data = getWeatherInfos(lat, lon);
            if (data == null || data.getCoord() == null) {
                throw new CoordinatesNotFoundException("Coordinates could not be found for the given location.");
            }
            return data.getCoord();
        }
    }

    @Override
    public Double getTemperature(Double lat, Double lon) {
        //return weatherInformation!=null? weatherInformation.getMain().getTemp():getWeatherInfos(lat,lon).getMain().getTemp() ;
        if(weatherInformation != null){
            return weatherInformation.getMain().getTemp();
        } else{
            WeatherInformation data = getWeatherInfos(lat, lon);
            if(data == null || data.getMain() == null) {
                throw new WeatherDataNotFoundException("Temperature Data is unavailable.");
            }
            return data.getMain().getTemp();
        }

    }

    @Override
    public String getName(Double lat, Double lon) {
        //return weatherInformation!=null? weatherInformation.getName():getWeatherInfos(lat,lon).getName() ;
        if (weatherInformation != null) {
            return weatherInformation.getName();
        } else {
            WeatherInformation data = getWeatherInfos(lat, lon);
            if (data == null) {
                throw new WeatherDataNotFoundException("Place's name is unavailable.");
            }
            return data.getName();
        }
    }

}
