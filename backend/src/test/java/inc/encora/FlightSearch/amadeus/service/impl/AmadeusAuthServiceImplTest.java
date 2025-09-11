package inc.encora.FlightSearch.amadeus.service.impl;

import inc.encora.FlightSearch.amadeus.client.AmadeusAuthClient;
import inc.encora.FlightSearch.amadeus.dto.request.AmadeusTokenRequestDTO;
import inc.encora.FlightSearch.amadeus.dto.response.AmadeusTokenResponseDTO;
import inc.encora.FlightSearch.amadeus.mapper.TokenMapper;
import inc.encora.FlightSearch.amadeus.model.Token;
import inc.encora.FlightSearch.amadeus.service.fixtures.AmadeusAuthServiceTestFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AmadeusAuthServiceImplTest {

    private static final String TEST_API_KEY = "test-api-key";
    private static final String TEST_API_SECRET = "test-api-secret";
    @Mock
    private AmadeusAuthClient amadeusAuthClient;
    @Mock
    private TokenMapper tokenMapper;
    private AmadeusAuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        authService = new AmadeusAuthServiceImpl(amadeusAuthClient, tokenMapper);
        ReflectionTestUtils.setField(authService, "apiKey", TEST_API_KEY);
        ReflectionTestUtils.setField(authService, "apiSecret", TEST_API_SECRET);
    }

    @Test
    void shouldGetAccessTokenWhenCacheIsEmpty() {
        // Given
        AmadeusTokenResponseDTO tokenResponse = AmadeusAuthServiceTestFixtures.createValidTokenResponse();
        Token expectedToken = AmadeusAuthServiceTestFixtures.createValidToken();

        when(amadeusAuthClient.requestAccessToken(any(AmadeusTokenRequestDTO.class)))
                .thenReturn(Mono.just(tokenResponse));
        when(tokenMapper.toToken(tokenResponse)).thenReturn(expectedToken);

        // When
        Token result = authService.getAccessToken();

        // Then
        assertThat(result).isEqualTo(expectedToken);
        assertThat(result.getValue()).isEqualTo("test-access-token-123");
        assertThat(result.isExpired()).isFalse();

        verify(amadeusAuthClient).requestAccessToken(any(AmadeusTokenRequestDTO.class));
        verify(tokenMapper).toToken(tokenResponse);
    }

    @Test
    void shouldReturnCachedTokenWhenTokenIsValid() {
        // Given - First call to populate cache
        AmadeusTokenResponseDTO tokenResponse = AmadeusAuthServiceTestFixtures.createValidTokenResponse();
        Token cachedToken = AmadeusAuthServiceTestFixtures.createValidToken();

        when(amadeusAuthClient.requestAccessToken(any(AmadeusTokenRequestDTO.class)))
                .thenReturn(Mono.just(tokenResponse));
        when(tokenMapper.toToken(tokenResponse)).thenReturn(cachedToken);

        // First call populates cache
        authService.getAccessToken();

        // When - Second call should use cache
        Token result = authService.getAccessToken();

        // Then
        assertThat(result).isEqualTo(cachedToken);
        verify(amadeusAuthClient, times(1)).requestAccessToken(any(AmadeusTokenRequestDTO.class));
        verify(tokenMapper, times(1)).toToken(tokenResponse);
    }

    @Test
    void shouldRefreshTokenWhenCachedTokenIsExpired() {
        // Given - First call with expired token
        AmadeusTokenResponseDTO expiredTokenResponse = AmadeusAuthServiceTestFixtures.createValidTokenResponse();
        Token expiredToken = AmadeusAuthServiceTestFixtures.createExpiredToken();

        when(amadeusAuthClient.requestAccessToken(any(AmadeusTokenRequestDTO.class)))
                .thenReturn(Mono.just(expiredTokenResponse));
        when(tokenMapper.toToken(expiredTokenResponse)).thenReturn(expiredToken);

        // Populate cache with expired token
        authService.getAccessToken();

        // Given - Second call should get fresh token
        AmadeusTokenResponseDTO freshTokenResponse = AmadeusAuthServiceTestFixtures.createValidTokenResponse();
        Token freshToken = AmadeusAuthServiceTestFixtures.createValidToken();
        freshToken.setValue("fresh-access-token");

        when(amadeusAuthClient.requestAccessToken(any(AmadeusTokenRequestDTO.class)))
                .thenReturn(Mono.just(freshTokenResponse));
        when(tokenMapper.toToken(freshTokenResponse)).thenReturn(freshToken);

        // When
        Token result = authService.getAccessToken();

        // Then
        assertThat(result).isEqualTo(freshToken);
        assertThat(result.getValue()).isEqualTo("fresh-access-token");
        verify(amadeusAuthClient, times(2)).requestAccessToken(any(AmadeusTokenRequestDTO.class));
        verify(tokenMapper, times(2)).toToken(any(AmadeusTokenResponseDTO.class));
    }

    @Test
    void shouldCreateCorrectTokenRequestWithCredentials() {
        // Given
        AmadeusTokenResponseDTO tokenResponse = AmadeusAuthServiceTestFixtures.createValidTokenResponse();
        Token token = AmadeusAuthServiceTestFixtures.createValidToken();

        when(amadeusAuthClient.requestAccessToken(any(AmadeusTokenRequestDTO.class)))
                .thenReturn(Mono.just(tokenResponse));
        when(tokenMapper.toToken(tokenResponse)).thenReturn(token);

        // When
        authService.getAccessToken();

        // Then
        ArgumentCaptor<AmadeusTokenRequestDTO> requestCaptor = ArgumentCaptor.forClass(AmadeusTokenRequestDTO.class);
        verify(amadeusAuthClient).requestAccessToken(requestCaptor.capture());

        AmadeusTokenRequestDTO capturedRequest = requestCaptor.getValue();
        assertThat(capturedRequest.getGrantType()).isEqualTo("client_credentials");
        assertThat(capturedRequest.getClientId()).isEqualTo(TEST_API_KEY);
        assertThat(capturedRequest.getClientSecret()).isEqualTo(TEST_API_SECRET);
    }

    @Test
    void shouldHandleClientExceptionGracefully() {
        // Given
        RuntimeException clientException = new RuntimeException("Amadeus API unavailable");
        when(amadeusAuthClient.requestAccessToken(any(AmadeusTokenRequestDTO.class)))
                .thenReturn(Mono.error(clientException));

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authService.getAccessToken();
        });

        assertThat(exception.getMessage()).isEqualTo("Amadeus API unavailable");
        verify(amadeusAuthClient).requestAccessToken(any(AmadeusTokenRequestDTO.class));
        verifyNoInteractions(tokenMapper);
    }

    @Test
    void shouldRefreshTokenWhenCacheContainsNull() {
        // Given - First call returns null (simulating empty response)
        when(amadeusAuthClient.requestAccessToken(any(AmadeusTokenRequestDTO.class)))
                .thenReturn(Mono.empty());

        // First call caches null
        Token firstResult = authService.getAccessToken();
        assertThat(firstResult).isNull();

        // Given - Second call should fetch new token
        AmadeusTokenResponseDTO secondResponse = AmadeusAuthServiceTestFixtures.createValidTokenResponse();
        Token validToken = AmadeusAuthServiceTestFixtures.createValidToken();
        when(amadeusAuthClient.requestAccessToken(any(AmadeusTokenRequestDTO.class)))
                .thenReturn(Mono.just(secondResponse));
        when(tokenMapper.toToken(secondResponse)).thenReturn(validToken);

        // When
        Token secondResult = authService.getAccessToken();

        // Then
        assertThat(secondResult).isEqualTo(validToken);
        verify(amadeusAuthClient, times(2)).requestAccessToken(any(AmadeusTokenRequestDTO.class));
        verify(tokenMapper, times(1)).toToken(any(AmadeusTokenResponseDTO.class));
    }
}
