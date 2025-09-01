package inc.encora.FlightSearch.amadeus.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusDictonariesDTO;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusMetaDTO;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmadeusListResponseDTO<T> {
    private AmadeusMetaDTO meta;
    private List<T> data;
    private AmadeusDictonariesDTO dictionaries;
}