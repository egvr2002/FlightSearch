package inc.encora.FlightSearch.amadeus.dto.response;

import inc.encora.FlightSearch.amadeus.dto.common.AmadeusIssueDTO;
import lombok.Data;

import java.util.List;

@Data
public class AmadeusErrorResponseDTO {
    private List<AmadeusIssueDTO> errors;
}
