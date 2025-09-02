package inc.encora.FlightSearch.amadeus.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmadeusSegmentDTO {
    private AmadeusFlightEndPointDTO departure;
    private AmadeusFlightEndPointDTO arrival;
    private String carrierCode;
    private String number;
    private AmadeusSegmentAircraftDTO aircraft;
    private AmadeusOperatingAirlineDTO operating;
    private String duration;
    private String id;
    private long numberOfStops;
    private boolean blacklistedInEU;
    private List<AmadeusFlightStopDTO> stops;
}
