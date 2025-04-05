package com.weatherApp.weatherApp.Entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Embeddable;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)


@JsonPropertyOrder({
        "lon",
        "lat"
})

@Embeddable
public class Coord {
    @Override
    public String toString() {
        return "Coord{" +
                "lon=" + lon +
                ", lat=" + lat +
                '}';
    }

    @JsonProperty("lon")
    private Double lon;

    public Coord(Double lat, Double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    @JsonProperty("lat")
    private Double lat;


    @JsonProperty("lon")
    public Double getLon() {
        return lon;
    }

    @JsonProperty("lon")
    public void setLon(Double lon) {
        this.lon = lon;
    }

    @JsonProperty("lat")
    public Double getLat() {
        return lat;
    }

    @JsonProperty("lat")
    public void setLat(Double lat) {
        this.lat = lat;
    }
}