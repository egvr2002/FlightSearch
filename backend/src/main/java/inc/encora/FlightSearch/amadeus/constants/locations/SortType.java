package inc.encora.FlightSearch.amadeus.constants.locations;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SortType {
    TRAVELERS_SCORE("analytics.travelers.score");

    private final String value;

    @Override
    public String toString() {
        return this.value;
    }
}
