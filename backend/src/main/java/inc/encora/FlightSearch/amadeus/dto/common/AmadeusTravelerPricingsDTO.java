package inc.encora.FlightSearch.amadeus.dto.common;

import lombok.Data;

import java.util.List;

@Data
public class AmadeusTravelerPricingsDTO {
    private String travelerId;
    private String fareOption;
    private String travelerType;
    private AmadeusTravelerPricingPriceDTO price;
    private List<AmadeusFareDetailsBySegmentDTO> fareDetailsBySegment;
}
