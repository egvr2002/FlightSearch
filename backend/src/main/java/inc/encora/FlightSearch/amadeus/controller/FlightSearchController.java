package inc.encora.FlightSearch.amadeus.controller;

import inc.encora.FlightSearch.amadeus.dto.request.SearchLocationsDTO;
import inc.encora.FlightSearch.amadeus.service.FlightSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class FlightSearchController {
    private final FlightSearchService flightSearchService;

    @GetMapping("/locations/airport")
    public ResponseEntity<?> searchLocations(SearchLocationsDTO searchLocationsDTO) {
        return ResponseEntity.ok(flightSearchService.searchLocations(searchLocationsDTO));
    }

    @GetMapping("/locations/city/{iataCode}")
    public ResponseEntity<?> searchCityByIATACode(@PathVariable String iataCode) {
        return ResponseEntity.ok(flightSearchService.searchCityByIATACode(iataCode));
    }

    @GetMapping("/airlines/{airlineCode}")
    public ResponseEntity<?> searchAirlineInformationByAirlineCode(@PathVariable String airlineCode) {
        return ResponseEntity.ok(flightSearchService.searchAirlineInformation(airlineCode));
    }
}
