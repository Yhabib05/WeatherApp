package com.weatherApp.weatherApp.Controllers;
import com.weatherApp.weatherApp.Entities.Coord;
import com.weatherApp.weatherApp.Entities.Main;
import com.weatherApp.weatherApp.Entities.WeatherInformation;
import com.weatherApp.weatherApp.Services.CoordinatesService;
import com.weatherApp.weatherApp.Services.WeatherService;
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

    /*
    @GetMapping("/by-city/{city}")
    public ResponseEntity<?>getWeatherByCity(@PathVariable String city){
        Coord coord = coordinatesService.getCoordinates(city);
        WeatherInformation data = weatherService.getWeatherInfos(coord.getLat(),coord.getLon());
        return ResponseEntity.ok(data);
    }
*/
    @PostMapping
    public ResponseEntity<?>getWeatherInfos(@RequestBody Coord coord){
        WeatherInformation data = weatherService.getWeatherInfos(coord.getLat(),coord.getLon());
        //return new ResponseEntity<>(data, HttpStatus.OK);
        return ResponseEntity.ok(data);
        //return ResponseEntity.status(HttpStatus.OK).body(data);
    }

    @PostMapping(value ="/main")
    public ResponseEntity<?> getMain(@RequestBody Coord coord) {
        Main main = weatherService.getMain(coord.getLat(),coord.getLon());
        return ResponseEntity.ok(main);
    }

    @PostMapping(value="/coordinates")
    public ResponseEntity<?> getCoordinates(@RequestBody Coord coord) {
        Coord coordinates = weatherService.getCoordinates(coord.getLat(),coord.getLon());
        //jackson take care of the serialization here
        return ResponseEntity.ok(coordinates);
    }

    @PostMapping(value="/temperature")
    public ResponseEntity<?> getTemperature(@RequestBody Coord coord){
        Double temperature = weatherService.getTemperature(coord.getLat(),coord.getLon());
        return ResponseEntity.ok( temperature);
    }

    @PostMapping(value="/name")
    public ResponseEntity<?> getName(@RequestBody Coord coord){
        String name = weatherService.getName(coord.getLat(),coord.getLon());
        return ResponseEntity.ok(name);
    }
}
