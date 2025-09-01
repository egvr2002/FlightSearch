package inc.encora.FlightSearch.amadeus.dto.common;

import lombok.Data;

@Data
public class AmadeusAirlineInformationDTO {
    private String type;
    private String iataCode;
    private String icaoCode;
    private String businessName;
    private String commonName;
}
