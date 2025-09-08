package inc.encora.FlightSearch.amadeus.mapper;

import inc.encora.FlightSearch.amadeus.dto.response.AmadeusTokenResponseDTO;
import inc.encora.FlightSearch.amadeus.model.Token;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TokenMapperTest {

    @Autowired
    private TokenMapper mapper;

    @Test
    void shouldMapAmadeusTokenResponseDTOToTokenWithValueAndExpiresAt() {
        AmadeusTokenResponseDTO dto = new AmadeusTokenResponseDTO();
        dto.setAccessToken("secret-token-abc");
        dto.setExpiresIn(120L); // 2 minutes

        Instant before = Instant.now();
        Token result = mapper.toToken(dto);
        Instant after = Instant.now();

        assertNotNull(result);
        assertEquals("secret-token-abc", result.getValue());
        assertNotNull(result.getExpiresAt());

        // expiresAt should be roughly now + 120 seconds
        assertTrue(result.getExpiresAt().isAfter(before));
        assertTrue(result.getExpiresAt().isBefore(after.plusSeconds(130)));

        // not expired immediately
        assertFalse(result.isExpired());
    }

    @Test
    void shouldReturnNullWhenTokenResponseDTOIsNull() {
        Token result = mapper.toToken(null);
        assertNull(result);
    }
}
