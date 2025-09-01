package inc.encora.FlightSearch.amadeus.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmadeusSelfDTO {
    private String self;
    private String next;
    private String previous;
    private String last;
    private String first;
    private String up;
}