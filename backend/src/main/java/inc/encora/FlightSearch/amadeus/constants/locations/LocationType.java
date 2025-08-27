package inc.encora.FlightSearch.amadeus.constants.locations;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LocationType {
    CITY("CITY"),
    AIRPORT("AIRPORT");

    private final String value;

    @Override
    public String toString() {
        return this.value;
    }
}