package inc.encora.FlightSearch.amadeus.dto.common;

import lombok.Data;

import java.util.List;

@Data
public class AmadeusPriceDTO {
    private String currency;
    private String total;
    private String base;
    private List<AmadeusFeeDTO> fees;
    private String grandTotal;
}
