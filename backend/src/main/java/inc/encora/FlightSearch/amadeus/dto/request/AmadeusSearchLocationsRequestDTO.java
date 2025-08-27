package inc.encora.FlightSearch.amadeus.dto.request;

import inc.encora.FlightSearch.amadeus.constants.locations.LocationType;
import inc.encora.FlightSearch.amadeus.constants.locations.SortType;
import inc.encora.FlightSearch.amadeus.constants.locations.ViewType;
import lombok.Builder;
import lombok.Data;

// TODO: Add support for multiple locations type, eg: "AIRPORT", "CITY", but also "AIRPORT,CITY"
@Data
@Builder
public class AmadeusSearchLocationsRequestDTO {
    private String keyword;

    @Builder.Default
    private int pageLimit = 10;

    @Builder.Default
    private int pageOffset = 0;

    @Builder.Default
    private LocationType subType = LocationType.AIRPORT;

    @Builder.Default
    private SortType sort = SortType.TRAVELERS_SCORE;

    @Builder.Default
    private ViewType view = ViewType.LIGHT;
}