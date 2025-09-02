package inc.encora.FlightSearch.amadeus.dto.common;

import lombok.Data;

import java.util.List;

@Data
public class AmadeusItineraryDTO {
    private String duration;
    private List<AmadeusSegmentDTO> segments;
}
