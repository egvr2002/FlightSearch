package inc.encora.FlightSearch.amadeus.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AmadeusAirlinesLookupRequestDTO {
    private String airlineCodes;
}
