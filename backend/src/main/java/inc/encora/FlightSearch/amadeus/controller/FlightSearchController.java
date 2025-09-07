package inc.encora.FlightSearch.amadeus.controller;

import inc.encora.FlightSearch.amadeus.dto.request.SearchFlightsDTO;
import inc.encora.FlightSearch.amadeus.dto.request.SearchLocationsDTO;
import inc.encora.FlightSearch.amadeus.service.FlightSearchService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
@CrossOrigin({"http://localhost:5173", "http://localhost:4173"})
@Validated
public class FlightSearchController {
    private final FlightSearchService flightSearchService;

    @GetMapping("/locations/airport")
    public ResponseEntity<?> searchLocations(@Valid SearchLocationsDTO searchLocationsDTO) {
        return ResponseEntity.ok(flightSearchService.searchLocations(searchLocationsDTO));
    }

    @GetMapping("/locations/city/{iataCode}")
    public ResponseEntity<?> searchCityByIATACode(
            @Pattern(regexp = "^[a-zA-Z]{3}$", message = "IATA code must be 3 letters")
            @PathVariable String iataCode
    ) {
        return ResponseEntity.ok(flightSearchService.searchCityByIATACode(iataCode));
    }

    @GetMapping("/airlines/{airlineCode}")
    public ResponseEntity<?> searchAirlineInformationByAirlineCode(
            @Pattern(regexp = "^[a-zA-Z0-9]{2,3}$", message = "Airline code must be 2 letters or digits")
            @PathVariable String airlineCode
    ) {
        return ResponseEntity.ok(flightSearchService.searchAirlineInformation(airlineCode));
    }

    @PostMapping("/flights")
    public ResponseEntity<?> searchFlightOffers(@Valid @RequestBody SearchFlightsDTO searchLocationsDTO) {
        return ResponseEntity.ok(flightSearchService.searchFlightOffers(searchLocationsDTO));
    }
}
