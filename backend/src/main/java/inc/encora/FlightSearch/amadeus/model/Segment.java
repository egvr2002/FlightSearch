package inc.encora.FlightSearch.amadeus.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Segment {
    private String segmentId;
    private FlightEndPoint departure;
    private FlightEndPoint arrival;
    private String carrierCode;
    private String operatingCarrierCode;
    private List<FlightStop> stops;
    private String number;
    private String aircraftCode;
}
