package com.weatherApp.weatherApp.Services.Impl;

import com.weatherApp.weatherApp.Entities.Coord;
import com.weatherApp.weatherApp.Entities.Main;
import com.weatherApp.weatherApp.Entities.WeatherInformation;
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

        String url =String.format("https://api.openweathermap.org/data/2.5/weather?lat=%f&lon=%f&appid=%s",lat,lon,weatherApiKey) ;
        RestTemplate restTemplate = new RestTemplate();
        weatherInformation=restTemplate.getForObject(url, WeatherInformation.class);
        return weatherInformation;
    }


    @Override
    public Main getMain(Double lat, Double lon) {
        return weatherInformation!=null? weatherInformation.getMain(): getWeatherInfos(lat,lon).getMain() ;
    }

    @Override
    public Coord getCoordinates(Double lat, Double lon) {
        return weatherInformation!=null? weatherInformation.getCoord(): getWeatherInfos(lat,lon).getCoord();
    }

    @Override
    public Double getTemperature(Double lat, Double lon) {
        return weatherInformation!=null? weatherInformation.getMain().getTemp():getWeatherInfos(lat,lon).getMain().getTemp() ;
    }

    @Override
    public String getName(Double lat, Double lon) {
        return weatherInformation!=null? weatherInformation.getName():getWeatherInfos(lat,lon).getName() ;
    }

}
