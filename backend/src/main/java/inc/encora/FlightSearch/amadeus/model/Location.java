package inc.encora.FlightSearch.amadeus.model;

import lombok.Data;

@Data
public class Location {
    private String airportName;
    private String iataCode;
    private String cityName;
}
