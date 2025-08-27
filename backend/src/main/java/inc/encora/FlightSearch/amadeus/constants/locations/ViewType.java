package inc.encora.FlightSearch.amadeus.constants.locations;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ViewType {
    LIGHT("LIGHT");

    private final String value;

    @Override
    public String toString() {
        return this.value;
    }
}