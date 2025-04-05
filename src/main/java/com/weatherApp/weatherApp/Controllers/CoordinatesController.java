package com.weatherApp.weatherApp.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weatherApp.weatherApp.Entities.Coord;
import com.weatherApp.weatherApp.Services.CoordinatesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/coord")
public class CoordinatesController {

    CoordinatesService coordinatesService;

    public CoordinatesController(CoordinatesService coordinatesService) {
        this.coordinatesService = coordinatesService;
    }

    @GetMapping
    public ResponseEntity<Coord> getCoordinates(@RequestParam String city){
        Coord data = coordinatesService.getCoordinates(city);
        if (data == null) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build(); // 503 with empty body
        }

        return ResponseEntity.ok(data);
    }
}
