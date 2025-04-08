package com.weatherApp.weatherApp.Entities;

public class WeatherInformation {
    private Coord coord;
    private Main main;
    private String name;

    @Override
    public String toString() {
        return "WeatherInformation{" +
                "coord=" + coord +
                ", main=" + main +
                ", name='" + name + '\'' +
                '}';
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}