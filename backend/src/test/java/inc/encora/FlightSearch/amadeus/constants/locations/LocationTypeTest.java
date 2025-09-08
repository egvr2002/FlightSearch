package inc.encora.FlightSearch.amadeus.constants.locations;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LocationTypeTest {

    @Test
    void shouldHaveCityEnumValue() {
        assertThat(LocationType.CITY).isNotNull();
        assertThat(LocationType.CITY.toString()).isEqualTo("CITY");
    }

    @Test
    void shouldHaveAirportEnumValue() {
        assertThat(LocationType.AIRPORT).isNotNull();
        assertThat(LocationType.AIRPORT.toString()).isEqualTo("AIRPORT");
    }

    @Test
    void shouldHaveCorrectStringRepresentation() {
        assertThat(LocationType.CITY.toString()).isEqualTo("CITY");
        assertThat(LocationType.AIRPORT.toString()).isEqualTo("AIRPORT");
    }

    @Test
    void shouldHaveOnlyTwoEnumValues() {
        LocationType[] values = LocationType.values();
        assertThat(values).hasSize(2);
        assertThat(values).containsExactlyInAnyOrder(LocationType.CITY, LocationType.AIRPORT);
    }

    @Test
    void shouldSupportValueOfOperation() {
        assertThat(LocationType.valueOf("CITY")).isEqualTo(LocationType.CITY);
        assertThat(LocationType.valueOf("AIRPORT")).isEqualTo(LocationType.AIRPORT);
    }

    @Test
    void shouldHaveConsistentHashCodeAndEquals() {
        LocationType city1 = LocationType.CITY;
        LocationType city2 = LocationType.valueOf("CITY");
        LocationType airport = LocationType.AIRPORT;

        assertThat(city1).isEqualTo(city2);
        assertThat(city1.hashCode()).isEqualTo(city2.hashCode());
        assertThat(city1).isNotEqualTo(airport);
    }

    @Test
    void shouldHaveCorrectEnumProperties() {
        assertThat(LocationType.CITY.name()).isEqualTo("CITY");
        assertThat(LocationType.AIRPORT.name()).isEqualTo("AIRPORT");

        assertThat(LocationType.CITY.ordinal()).isEqualTo(0);
        assertThat(LocationType.AIRPORT.ordinal()).isEqualTo(1);
    }
}
