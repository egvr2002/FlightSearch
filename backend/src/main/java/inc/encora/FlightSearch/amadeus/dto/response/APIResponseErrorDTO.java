package inc.encora.FlightSearch.amadeus.dto.response;

import lombok.Data;

@Data
public class APIResponseErrorDTO {
    private String status;
    private String message;
}
