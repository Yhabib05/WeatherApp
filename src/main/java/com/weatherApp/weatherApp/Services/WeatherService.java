package com.weatherApp.weatherApp.Services;

import com.weatherApp.weatherApp.Entities.Coord;
import com.weatherApp.weatherApp.Entities.Main;
import com.weatherApp.weatherApp.Entities.WeatherInformation;

public interface WeatherService {
    public WeatherInformation getWeatherInfos(Double lat, Double lon);
    public Main getMain(Double lat, Double lon);
    public Coord getCoordinates(Double lat, Double lon);

    public Double getTemperature(Double lat, Double lon);
    public String getName(Double lat, Double lon);
}