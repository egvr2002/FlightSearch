package inc.encora.FlightSearch.amadeus.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightEndPoint {
    private String iataCode;
    private LocalDateTime at;
}
