package inc.encora.FlightSearch.amadeus.dto.common;

import lombok.Data;

@Data
public class AmadeusAmenityDTO {
    private String description;
    private boolean isChargeable;
    private String amenityType;
    private AmadeusAmenityProviderDTO amenityProvider;
}
