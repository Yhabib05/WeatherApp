package com.weatherApp.weatherApp.Services;

import com.weatherApp.weatherApp.Entities.Coord;

public interface CoordinatesService {
    public Coord getCoordinates(String city);
}
