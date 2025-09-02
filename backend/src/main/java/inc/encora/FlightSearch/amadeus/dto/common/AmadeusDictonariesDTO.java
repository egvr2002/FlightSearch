package inc.encora.FlightSearch.amadeus.dto.common;

import lombok.Data;

import java.util.Map;

@Data
public class AmadeusDictonariesDTO {
    private Map<String, AmadeusLocationValueDTO> locations;
    private Map<String, String> aircraft;
    private Map<String, String> currencies;
    private Map<String, String> carriers;
}
