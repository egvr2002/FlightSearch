package inc.encora.FlightSearch.amadeus.constants.locations;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ViewTypeTest {

    @Test
    void shouldHaveLightEnumValue() {
        assertThat(ViewType.LIGHT).isNotNull();
        assertThat(ViewType.LIGHT.toString()).isEqualTo("LIGHT");
    }

    @Test
    void shouldHaveCorrectStringRepresentation() {
        assertThat(ViewType.LIGHT.toString()).isEqualTo("LIGHT");
    }

    @Test
    void shouldHaveOnlyOneEnumValue() {
        ViewType[] values = ViewType.values();
        assertThat(values).hasSize(1);
        assertThat(values).containsExactly(ViewType.LIGHT);
    }

    @Test
    void shouldSupportValueOfOperation() {
        assertThat(ViewType.valueOf("LIGHT")).isEqualTo(ViewType.LIGHT);
    }

    @Test
    void shouldHaveConsistentHashCodeAndEquals() {
        ViewType light1 = ViewType.LIGHT;
        ViewType light2 = ViewType.valueOf("LIGHT");

        assertThat(light1).isEqualTo(light2);
        assertThat(light1.hashCode()).isEqualTo(light2.hashCode());
    }

    @Test
    void shouldHaveCorrectEnumProperties() {
        assertThat(ViewType.LIGHT.name()).isEqualTo("LIGHT");
        assertThat(ViewType.LIGHT.ordinal()).isEqualTo(0);
    }

    @Test
    void shouldReturnCorrectValueForAmadeusApiParameter() {
        // This test verifies that the toString() method returns the exact value
        // expected by Amadeus API
        String expectedApiValue = "LIGHT";
        assertThat(ViewType.LIGHT.toString()).isEqualTo(expectedApiValue);
    }

    @Test
    void shouldBeUsableInStringConcatenation() {
        String query = "view=" + ViewType.LIGHT;
        assertThat(query).isEqualTo("view=LIGHT");
    }

    @Test
    void shouldBeUsableInQueryParameters() {
        // Test practical usage in URL parameters
        String queryParam = String.format("view=%s", ViewType.LIGHT);
        assertThat(queryParam).isEqualTo("view=LIGHT");
    }
}
