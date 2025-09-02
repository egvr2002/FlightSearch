package inc.encora.FlightSearch.amadeus.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AmadeusSearchFlightsRequestDTO {
    private String originLocationCode;
    private String destinationLocationCode;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private int adults;
    private boolean nonStop;
    private int max;
    private String currencyCode;
}
