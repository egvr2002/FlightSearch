package inc.encora.FlightSearch.amadeus.dto.common;

import lombok.Data;

import java.util.List;

@Data
public class AmadeusPricingOptionsDTO {
    private List<String> fareType;
    private boolean includedCheckedBagsOnly;
}
