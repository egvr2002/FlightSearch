package inc.encora.FlightSearch.amadeus.dto.common;

import inc.encora.FlightSearch.amadeus.constants.locations.LocationType;
import lombok.Data;

@Data
public class AmadeusLocationDTO {
    private String type;
    private LocationType subType;
    private String name;
    private String detailedName;
    private String id;
    private AmadeusSelfDTO self;
    private String iataCode;
    private AmadeusAddressDTO address;
}