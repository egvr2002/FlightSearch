package inc.encora.FlightSearch.amadeus.model;

import lombok.Data;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Data
public class Token {
    private String value;
    private Instant expiresAt;

    public boolean isExpired() {
        return Instant.now().isAfter(expiresAt.minus(30, ChronoUnit.SECONDS));
    }
}
