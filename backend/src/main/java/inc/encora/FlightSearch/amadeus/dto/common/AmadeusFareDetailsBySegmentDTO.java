package inc.encora.FlightSearch.amadeus.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmadeusFareDetailsBySegmentDTO {
    private String segmentId;
    private String cabin;
    private String fareBasis;
    private String brandedFare;
    private String brandedFareLabel;
    private AmadeusIncludedCBagsDTO includedCheckedBags;
    private AmadeusIncludedCBagsDTO includedCabinBags;
    private List<AmadeusAmenityDTO> amenities;

    @JsonProperty("class")
    private String bookingClass;
}
