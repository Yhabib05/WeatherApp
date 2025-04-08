package com.weatherApp.weatherApp.Controllers;
import com.weatherApp.weatherApp.Entities.Coord;
import com.weatherApp.weatherApp.Entities.Main;
import com.weatherApp.weatherApp.Entities.WeatherInformation;
import com.weatherApp.weatherApp.Services.CoordinatesService;
import com.weatherApp.weatherApp.Services.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;
    private final CoordinatesService coordinatesService;

    public WeatherController(WeatherService weatherService, CoordinatesService coordinatesService){
        this.weatherService=weatherService;
        this.coordinatesService = coordinatesService;
    }

    @GetMapping("/by-city")
    public ResponseEntity<?>getWeatherByCity(@RequestParam String city){
        Coord coord = coordinatesService.getCoordinates(city);

        if(coord==null){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Could not find coordinates for city: " + city);
        }
        WeatherInformation data = weatherService.getWeatherInfos(coord.getLat(),coord.getLon());

        if(data==null){
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Weather data is currently unavailable.");
        }
        return ResponseEntity.ok(data);
    }

    @GetMapping
    public ResponseEntity<?>getWeatherInfos(@RequestBody Coord coord){
        Double lat = coord.getLat();
        Double lon = coord.getLon();

        WeatherInformation data = weatherService.getWeatherInfos(lat,lon);

        if(data==null){
            //this is same as: new ResponseEntity<>("Weather data is currently unavailable.", HttpStatus.SERVICE_UNAVAILABLE)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Weather data is currently unavailable.");
        }
        //return new ResponseEntity<>(data, HttpStatus.OK);
        return ResponseEntity.ok(data);
        //return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @GetMapping(value ="/main")
    public ResponseEntity<?> getMain(@RequestBody Coord coord) {
        Main main = weatherService.getMain(coord.getLat(),coord.getLon());
        if(main==null) {
            //this is same as: new ResponseEntity<>("Weather data is currently unavailable.", HttpStatus.SERVICE_UNAVAILABLE)
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Weather data is currently unavailable.");
        }
        //return new ResponseEntity<>(main, HttpStatus.OK);
        //return ResponseEntity.status(HttpStatus.OK).body(data);

        //when doing this concatenation  java call the twoString method in my class, so we need to overide it
        //so that it shows our objet the correct way
        return ResponseEntity.ok(main);
    }

    @GetMapping(value="/coordinates")
    public ResponseEntity<?> getCoordinates(@RequestBody Coord coord) {
        Coord coordinates = weatherService.getCoordinates(coord.getLat(),coord.getLon());
        if(coordinates==null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Place coordinates couldn't be found");
        }
        //jackson take care of the serialization here
        return ResponseEntity.ok(coordinates);
    }

    @GetMapping(value="/temperature")
    public ResponseEntity<?> getTemperature(@RequestBody Coord coord){
        Double temperature = weatherService.getTemperature(coord.getLat(),coord.getLon());
        if(temperature==null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Temperature is not defined.");
        }
        return ResponseEntity.ok("temperature: " + temperature);
    }

    @GetMapping(value="/name")
    public ResponseEntity<?> getName(@RequestBody Coord coord){
        String name = weatherService.getName(coord.getLat(),coord.getLon());
        if(name==null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Place's name is not defined.");
        }
        return ResponseEntity.ok("name: "  + name);
    }
}
