package inc.encora.FlightSearch.amadeus.dto.request;

import lombok.Data;

@Data
public class SearchLocationsDTO {
    private String keyword;
    private String subType;
    private String sort;
    private String view;
    private int pageLimit = 10;
    private int pageOffset = 0;
}
