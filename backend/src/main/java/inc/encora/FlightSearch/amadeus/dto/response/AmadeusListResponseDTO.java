package inc.encora.FlightSearch.amadeus.dto.response;

import inc.encora.FlightSearch.amadeus.dto.common.AmadeusMetaDTO;
import lombok.Data;

import java.util.List;

@Data
public class AmadeusListResponseDTO<T> {
    private AmadeusMetaDTO meta;
    private List<T> data;
}