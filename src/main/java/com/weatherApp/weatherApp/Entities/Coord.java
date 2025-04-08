package com.weatherApp.weatherApp.Entities;
import jakarta.persistence.Embeddable;


@Embeddable
public class Coord {

    @Override
    public String toString() {
        return "Coord{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}';
    }

    private Double lon;
    private Double lat;

    public Coord() {
    }
    public Coord(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public Double getLon() {
        return lon;
    }
    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }
    public void setLat(Double lat) {
        this.lat = lat;
    }
}