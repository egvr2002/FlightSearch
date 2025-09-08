package inc.encora.FlightSearch.amadeus.service.fixtures;

import inc.encora.FlightSearch.amadeus.dto.request.AmadeusTokenRequestDTO;
import inc.encora.FlightSearch.amadeus.dto.response.AmadeusTokenResponseDTO;
import inc.encora.FlightSearch.amadeus.model.Token;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Test fixtures for AmadeusAuthService tests
 */
public class AmadeusAuthServiceTestFixtures {

    public static AmadeusTokenRequestDTO createValidTokenRequest() {
        return new AmadeusTokenRequestDTO(
                "client_credentials",
                "test-client-id",
                "test-client-secret");
    }

    public static AmadeusTokenRequestDTO createTokenRequestWithCustomCredentials(String clientId, String clientSecret) {
        return new AmadeusTokenRequestDTO(
                "client_credentials",
                clientId,
                clientSecret);
    }

    public static AmadeusTokenResponseDTO createValidTokenResponse() {
        AmadeusTokenResponseDTO response = new AmadeusTokenResponseDTO();
        response.setAccessToken("test-access-token-123");
        response.setTokenType("Bearer");
        response.setExpiresIn(3600L); // 1 hour
        response.setType("amadeusOAuth2Token");
        response.setUsername("test-user");
        response.setApplicationName("FlightSearch");
        response.setClientId("test-client-id");
        response.setState("approved");
        response.setScope("read write");
        return response;
    }

    public static AmadeusTokenResponseDTO createShortLivedTokenResponse() {
        AmadeusTokenResponseDTO response = new AmadeusTokenResponseDTO();
        response.setAccessToken("short-lived-token");
        response.setTokenType("Bearer");
        response.setExpiresIn(60L); // 1 minute
        response.setType("amadeusOAuth2Token");
        response.setUsername("test-user");
        response.setApplicationName("FlightSearch");
        response.setClientId("test-client-id");
        response.setState("approved");
        response.setScope("read write");
        return response;
    }

    public static AmadeusTokenResponseDTO createTokenResponseWithCustomValues(String accessToken, long expiresIn) {
        AmadeusTokenResponseDTO response = new AmadeusTokenResponseDTO();
        response.setAccessToken(accessToken);
        response.setTokenType("Bearer");
        response.setExpiresIn(expiresIn);
        response.setType("amadeusOAuth2Token");
        response.setUsername("test-user");
        response.setApplicationName("FlightSearch");
        response.setClientId("test-client-id");
        response.setState("approved");
        response.setScope("read write");
        return response;
    }

    public static Token createValidToken() {
        Token token = new Token();
        token.setValue("test-access-token-123");
        token.setExpiresAt(Instant.now().plus(1, ChronoUnit.HOURS));
        return token;
    }

    public static Token createExpiredToken() {
        Token token = new Token();
        token.setValue("expired-token");
        token.setExpiresAt(Instant.now().minus(1, ChronoUnit.HOURS));
        return token;
    }

    public static Token createAlmostExpiredToken() {
        Token token = new Token();
        token.setValue("almost-expired-token");
        // Token expires in 25 seconds (less than the 30-second buffer)
        token.setExpiresAt(Instant.now().plus(25, ChronoUnit.SECONDS));
        return token;
    }

    public static Token createTokenWithCustomExpiry(String value, Instant expiresAt) {
        Token token = new Token();
        token.setValue(value);
        token.setExpiresAt(expiresAt);
        return token;
    }

    public static Token createLongLivedToken() {
        Token token = new Token();
        token.setValue("long-lived-token");
        token.setExpiresAt(Instant.now().plus(24, ChronoUnit.HOURS));
        return token;
    }

    public static Token createTokenExpiringIn(long seconds) {
        Token token = new Token();
        token.setValue("token-expiring-in-" + seconds);
        token.setExpiresAt(Instant.now().plus(seconds, ChronoUnit.SECONDS));
        return token;
    }

    // Utility methods for test assertions
    public static boolean isTokenValid(Token token) {
        return token != null && token.getValue() != null && !token.isExpired();
    }

    public static boolean isTokenExpired(Token token) {
        return token != null && token.isExpired();
    }
}
