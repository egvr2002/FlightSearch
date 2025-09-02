package inc.encora.FlightSearch.amadeus.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class FareDetailsBySegment {
    private String segmentId;
    private String cabin;
    private List<Amenity> amenities;

    @JsonProperty("class")
    private String bookingClass;
}
