package inc.encora.FlightSearch.amadeus.client;

import inc.encora.FlightSearch.amadeus.client.fixtures.AmadeusClientTestFixtures;
import inc.encora.FlightSearch.amadeus.dto.request.AmadeusTokenRequestDTO;
import inc.encora.FlightSearch.amadeus.dto.response.AmadeusTokenResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AmadeusAuthClientTest {

    @Mock
    private WebClient mockWebClient;

    @Mock
    private WebClient.RequestBodyUriSpec mockRequestBodyUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec mockRequestHeadersSpec;

    @Mock
    private WebClient.RequestBodySpec mockRequestBodySpec;

    @Mock
    private WebClient.ResponseSpec mockResponseSpec;

    private AmadeusAuthClient authClient;

    @BeforeEach
    void setUp() {
        authClient = new AmadeusAuthClient(mockWebClient);
    }

    @Test
    void shouldRequestAccessTokenSuccessfully() {
        // Given
        AmadeusTokenRequestDTO request = AmadeusClientTestFixtures.createTokenRequest();
        AmadeusTokenResponseDTO expectedResponse = AmadeusClientTestFixtures.createTokenResponse();

        // Mock WebClient chain
        when(mockWebClient.post()).thenReturn(mockRequestBodyUriSpec);
        when(mockRequestBodyUriSpec.uri("/v1/security/oauth2/token")).thenReturn(mockRequestBodySpec);
        when(mockRequestBodySpec.header(org.mockito.ArgumentMatchers.anyString(),
                org.mockito.ArgumentMatchers.<String[]>any()))
                .thenReturn(mockRequestBodySpec);
        when(mockRequestBodySpec.body(org.mockito.ArgumentMatchers.any()))
                .thenReturn(mockRequestHeadersSpec);
        when(mockRequestHeadersSpec.retrieve()).thenReturn(mockResponseSpec);
        when(mockResponseSpec.bodyToMono(AmadeusTokenResponseDTO.class))
                .thenReturn(Mono.just(expectedResponse));

        // When
        AmadeusTokenResponseDTO result = authClient.requestAccessToken(request).block();

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getType()).isEqualTo(expectedResponse.getType());
        assertThat(result.getUsername()).isEqualTo(expectedResponse.getUsername());
        assertThat(result.getAccessToken()).isEqualTo(expectedResponse.getAccessToken());
        assertThat(result.getApplicationName()).isEqualTo(expectedResponse.getApplicationName());
        assertThat(result.getClientId()).isEqualTo(expectedResponse.getClientId());
        assertThat(result.getTokenType()).isEqualTo(expectedResponse.getTokenType());
        assertThat(result.getAccessToken()).isEqualTo(expectedResponse.getAccessToken());
        assertThat(result.getExpiresIn()).isEqualTo(expectedResponse.getExpiresIn());
        assertThat(result.getState()).isEqualTo(expectedResponse.getState());
        assertThat(result.getScope()).isEqualTo(expectedResponse.getScope());
    }

    @SuppressWarnings("unchecked")
    @Test
    void shouldHandleEmptyResponseFromTokenEndpoint() {
        // Given
        AmadeusTokenRequestDTO request = AmadeusClientTestFixtures.createTokenRequest();

        // Mock WebClient chain to return empty
        when(mockWebClient.post()).thenReturn(mockRequestBodyUriSpec);
        when(mockRequestBodyUriSpec.uri("/v1/security/oauth2/token")).thenReturn(mockRequestBodySpec);
        when(mockRequestBodySpec.header(ArgumentMatchers.anyString(), ArgumentMatchers.<String[]>any()))
                .thenReturn(mockRequestBodySpec);
        when(mockRequestBodySpec.body(ArgumentMatchers.any()))
                .thenReturn(mockRequestHeadersSpec);
        when(mockRequestHeadersSpec.retrieve()).thenReturn(mockResponseSpec);
        when(mockResponseSpec.bodyToMono(AmadeusTokenResponseDTO.class)).thenReturn(Mono.empty());

        // When
        AmadeusTokenResponseDTO result = authClient.requestAccessToken(request).block();

        // Then
        assertThat(result).isNull();
    }

    @SuppressWarnings("unchecked")
    @Test
    void shouldHandleErrorFromTokenEndpoint() {
        // Given
        AmadeusTokenRequestDTO request = AmadeusClientTestFixtures.createTokenRequest();
        RuntimeException expectedError = new RuntimeException("Auth service unavailable");

        // Mock WebClient chain to return error
        when(mockWebClient.post()).thenReturn(mockRequestBodyUriSpec);
        when(mockRequestBodyUriSpec.uri("/v1/security/oauth2/token")).thenReturn(mockRequestBodySpec);
        when(mockRequestBodySpec.header(ArgumentMatchers.anyString(), ArgumentMatchers.<String[]>any()))
                .thenReturn(mockRequestBodySpec);
        when(mockRequestBodySpec.body(ArgumentMatchers.any()))
                .thenReturn(mockRequestHeadersSpec);
        when(mockRequestHeadersSpec.retrieve()).thenReturn(mockResponseSpec);
        when(mockResponseSpec.bodyToMono(AmadeusTokenResponseDTO.class))
                .thenReturn(Mono.error(expectedError));

        // When & Then
        assertThrows(RuntimeException.class, () -> authClient.requestAccessToken(request).block());
    }
}
