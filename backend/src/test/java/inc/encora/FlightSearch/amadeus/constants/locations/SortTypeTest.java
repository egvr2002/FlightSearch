package inc.encora.FlightSearch.amadeus.constants.locations;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SortTypeTest {

    @Test
    void shouldHaveTravelersScoreEnumValue() {
        assertThat(SortType.TRAVELERS_SCORE).isNotNull();
        assertThat(SortType.TRAVELERS_SCORE.toString()).isEqualTo("analytics.travelers.score");
    }

    @Test
    void shouldHaveCorrectStringRepresentation() {
        assertThat(SortType.TRAVELERS_SCORE.toString()).isEqualTo("analytics.travelers.score");
    }

    @Test
    void shouldHaveOnlyOneEnumValue() {
        SortType[] values = SortType.values();
        assertThat(values).hasSize(1);
        assertThat(values).containsExactly(SortType.TRAVELERS_SCORE);
    }

    @Test
    void shouldSupportValueOfOperation() {
        assertThat(SortType.valueOf("TRAVELERS_SCORE")).isEqualTo(SortType.TRAVELERS_SCORE);
    }

    @Test
    void shouldHaveConsistentHashCodeAndEquals() {
        SortType score1 = SortType.TRAVELERS_SCORE;
        SortType score2 = SortType.valueOf("TRAVELERS_SCORE");

        assertThat(score1).isEqualTo(score2);
        assertThat(score1.hashCode()).isEqualTo(score2.hashCode());
    }

    @Test
    void shouldHaveCorrectEnumProperties() {
        assertThat(SortType.TRAVELERS_SCORE.name()).isEqualTo("TRAVELERS_SCORE");
        assertThat(SortType.TRAVELERS_SCORE.ordinal()).isEqualTo(0);
    }

    @Test
    void shouldReturnCorrectValueForAmadeusApiParameter() {
        String expectedApiValue = "analytics.travelers.score";
        assertThat(SortType.TRAVELERS_SCORE.toString()).isEqualTo(expectedApiValue);
    }

    @Test
    void shouldBeUsableInStringConcatenation() {
        String query = "sort=" + SortType.TRAVELERS_SCORE;
        assertThat(query).isEqualTo("sort=analytics.travelers.score");
    }
}
