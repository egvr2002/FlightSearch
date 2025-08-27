package inc.encora.FlightSearch.amadeus.service;

import inc.encora.FlightSearch.amadeus.dto.request.SearchLocationsDTO;
import inc.encora.FlightSearch.amadeus.model.Address;
import inc.encora.FlightSearch.amadeus.model.Location;

import java.util.List;

public interface FlightSearchService {
    List<Location> searchLocations(SearchLocationsDTO searchLocationsDTO);
    Address searchCityByIATACode(String iataCode);
}
