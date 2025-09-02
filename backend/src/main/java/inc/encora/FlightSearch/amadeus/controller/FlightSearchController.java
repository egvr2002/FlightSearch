package inc.encora.FlightSearch.amadeus.controller;

import inc.encora.FlightSearch.amadeus.dto.request.SearchFlightsDTO;
import inc.encora.FlightSearch.amadeus.dto.request.SearchLocationsDTO;
import inc.encora.FlightSearch.amadeus.service.FlightSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
@CrossOrigin({"http://localhost:5173", "http://localhost:4173"})
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

    @PostMapping("/flights")
    public ResponseEntity<?> searchFlightOffers(@RequestBody SearchFlightsDTO searchLocationsDTO) {
        return ResponseEntity.ok(flightSearchService.searchFlightOffers(searchLocationsDTO));
    }
}
