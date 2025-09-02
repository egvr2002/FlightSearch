package inc.encora.FlightSearch.amadeus.dto.common;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AmadeusFlightStopDTO {
    private String iataCode;
    private String duration;
    private LocalDateTime arrivalAt;
    private LocalDateTime departureAt;
}
