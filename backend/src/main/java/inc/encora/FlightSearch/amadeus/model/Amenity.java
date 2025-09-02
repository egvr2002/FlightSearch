package inc.encora.FlightSearch.amadeus.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Amenity {
    private String name;

    @JsonProperty("isChargeable")
    private boolean isChargeable;
}