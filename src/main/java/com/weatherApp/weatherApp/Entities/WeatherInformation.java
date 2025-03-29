package com.weatherApp.weatherApp.Entities;

import com.fasterxml.jackson.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

//to ignore null json fields during serialization
@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
        "coord",
        "main",
        "name"
})

public class WeatherInformation {
    @JsonProperty("coord")
    private Coord coord;
    @JsonProperty("main")
    private Main main;
    @JsonProperty("name")
    private String name;

    /*
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    */

    @Override
    public String toString() {
        return "WeatherInformation{" +
                "coord=" + coord +
                ", main=" + main +
                ", name='" + name + '\'' +
                '}';
    }

    @JsonProperty("coord")
    public Coord getCoord() {
        return coord;
    }

    @JsonProperty("coord")
    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    @JsonProperty("main")
    public Main getMain() {
        return main;
    }

    @JsonProperty("main")
    public void setMain(Main main) {
        this.main = main;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /*
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
    */

    /*@JsonAnySetter
    *public void setAdditionalProperty(String name, Object value) {
    *   this.additionalProperties.put(name, value);
    }*/

}