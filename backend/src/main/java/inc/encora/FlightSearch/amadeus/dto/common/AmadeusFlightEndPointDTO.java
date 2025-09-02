package inc.encora.FlightSearch.amadeus.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmadeusFlightEndPointDTO {
    private String iataCode;
    private String terminal;
    private LocalDateTime at;
}
