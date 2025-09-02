package inc.encora.FlightSearch.amadeus.model;

import lombok.Data;

import java.util.List;

@Data
public class TravelerPricing {
    private String travelerId;
    private String basePrice;
    private String totalPrice;
    private List<FareDetailsBySegment> fareDetailsBySegment;
}
