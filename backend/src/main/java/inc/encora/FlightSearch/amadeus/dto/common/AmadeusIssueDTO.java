package inc.encora.FlightSearch.amadeus.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmadeusIssueDTO {
    private int status;
    private int code;
    private String title;
    private String detail;
    private AmadeusIssueSourceDTO source;
}
