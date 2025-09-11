package inc.encora.FlightSearch.amadeus.client;

import inc.encora.FlightSearch.amadeus.client.fixtures.AmadeusClientTestFixtures;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusAirlineInformationDTO;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusLocationDTO;
import inc.encora.FlightSearch.amadeus.dto.request.AmadeusAirlinesLookupRequestDTO;
import inc.encora.FlightSearch.amadeus.dto.request.AmadeusSearchLocationsRequestDTO;
import inc.encora.FlightSearch.amadeus.dto.response.AmadeusListResponseDTO;
import inc.encora.FlightSearch.amadeus.exceptions.api.AmadeusAPIException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AmadeusLocationsClientTest {

    @Mock
    private WebClient mockWebClient;

    @Mock
    private WebClient.RequestHeadersUriSpec mockRequestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec mockRequestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec mockResponseSpec;

    private AmadeusLocationsClient locationsClient;

    @BeforeEach
    void setUp() {
        locationsClient = new AmadeusLocationsClient(mockWebClient);
    }

    @Test
    void shouldSearchLocationsSuccessfully() {
        // Given
        AmadeusSearchLocationsRequestDTO request = AmadeusClientTestFixtures.createLocationSearchRequest();
        AmadeusListResponseDTO<AmadeusLocationDTO> expectedResponse = AmadeusClientTestFixtures.createLocationsResponse();

        // Mock WebClient chain
        when(mockWebClient.get()).thenReturn(mockRequestHeadersUriSpec);
        when(mockRequestHeadersUriSpec.uri(any(Function.class))).thenReturn(mockRequestHeadersSpec);
        when(mockRequestHeadersSpec.retrieve()).thenReturn(mockResponseSpec);
        when(mockResponseSpec.bodyToMono(any(ParameterizedTypeReference.class)))
                .thenReturn(Mono.just(expectedResponse));

        // When
        AmadeusListResponseDTO<AmadeusLocationDTO> result = locationsClient.searchLocations(request).block();

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getData()).hasSize(2);
        assertThat(result.getMeta().getCount()).isEqualTo(2);
        assertThat(result.getData().get(0).getName()).isEqualTo("Madrid");
        assertThat(result.getData().get(1).getName()).isEqualTo("Mexico City");
    }

    @Test
    void shouldHandleEmptyLocationsResponse() {
        // Given
        AmadeusSearchLocationsRequestDTO request = AmadeusClientTestFixtures.createLocationSearchRequest();

        // Mock WebClient chain to return empty
        when(mockWebClient.get()).thenReturn(mockRequestHeadersUriSpec);
        when(mockRequestHeadersUriSpec.uri(any(Function.class))).thenReturn(mockRequestHeadersSpec);
        when(mockRequestHeadersSpec.retrieve()).thenReturn(mockResponseSpec);
        when(mockResponseSpec.bodyToMono(any(ParameterizedTypeReference.class))).thenReturn(Mono.empty());

        // When
        AmadeusListResponseDTO<AmadeusLocationDTO> result = locationsClient.searchLocations(request).block();

        // Then
        assertThat(result).isNull();
    }

    @Test
    void shouldHandleErrorFromLocationsEndpoint() {
        // Given
        AmadeusSearchLocationsRequestDTO request = AmadeusClientTestFixtures.createLocationSearchRequest();
        AmadeusAPIException expectedError = new AmadeusAPIException("Locations service unavailable");

        // Mock WebClient chain to return error
        when(mockWebClient.get()).thenReturn(mockRequestHeadersUriSpec);
        when(mockRequestHeadersUriSpec.uri(any(Function.class))).thenReturn(mockRequestHeadersSpec);
        when(mockRequestHeadersSpec.retrieve()).thenReturn(mockResponseSpec);
        when(mockResponseSpec.bodyToMono(any(ParameterizedTypeReference.class)))
                .thenReturn(Mono.error(expectedError));

        // When & Then
        assertThrows(AmadeusAPIException.class, () -> locationsClient.searchLocations(request).block());
    }

    @Test
    void shouldSearchAirlineInformationSuccessfully() {
        // Given
        AmadeusAirlinesLookupRequestDTO request = AmadeusClientTestFixtures.createAirlineLookupRequest();
        AmadeusListResponseDTO<AmadeusAirlineInformationDTO> expectedResponse = AmadeusClientTestFixtures
                .createAirlineInfoResponse();

        // Mock WebClient chain
        when(mockWebClient.get()).thenReturn(mockRequestHeadersUriSpec);
        when(mockRequestHeadersUriSpec.uri(any(Function.class))).thenReturn(mockRequestHeadersSpec);
        when(mockRequestHeadersSpec.retrieve()).thenReturn(mockResponseSpec);
        when(mockResponseSpec.bodyToMono(any(ParameterizedTypeReference.class)))
                .thenReturn(Mono.just(expectedResponse));

        // When
        AmadeusListResponseDTO<AmadeusAirlineInformationDTO> result = locationsClient.searchAirlineInformation(request)
                .block();

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getData()).hasSize(1);
        assertThat(result.getMeta().getCount()).isEqualTo(1);
        assertThat(result.getData().get(0).getIataCode()).isEqualTo("BA");
        assertThat(result.getData().get(0).getBusinessName()).isEqualTo("British Airways");
    }

    @Test
    void shouldHandleEmptyAirlineInformationResponse() {
        // Given
        AmadeusAirlinesLookupRequestDTO request = AmadeusClientTestFixtures.createAirlineLookupRequest();

        // Mock WebClient chain to return empty
        when(mockWebClient.get()).thenReturn(mockRequestHeadersUriSpec);
        when(mockRequestHeadersUriSpec.uri(any(Function.class))).thenReturn(mockRequestHeadersSpec);
        when(mockRequestHeadersSpec.retrieve()).thenReturn(mockResponseSpec);
        when(mockResponseSpec.bodyToMono(any(ParameterizedTypeReference.class))).thenReturn(Mono.empty());

        // When
        AmadeusListResponseDTO<AmadeusAirlineInformationDTO> result = locationsClient.searchAirlineInformation(request)
                .block();

        // Then
        assertThat(result).isNull();
    }

    @Test
    void shouldHandleErrorFromAirlineInformationEndpoint() {
        // Given
        AmadeusAirlinesLookupRequestDTO request = AmadeusClientTestFixtures.createAirlineLookupRequest();
        AmadeusAPIException expectedError = new AmadeusAPIException("Airline info service unavailable");

        // Mock WebClient chain to return error
        when(mockWebClient.get()).thenReturn(mockRequestHeadersUriSpec);
        when(mockRequestHeadersUriSpec.uri(any(Function.class))).thenReturn(mockRequestHeadersSpec);
        when(mockRequestHeadersSpec.retrieve()).thenReturn(mockResponseSpec);
        when(mockResponseSpec.bodyToMono(any(ParameterizedTypeReference.class)))
                .thenReturn(Mono.error(expectedError));

        // When & Then
        assertThrows(AmadeusAPIException.class, () -> locationsClient.searchAirlineInformation(request).block());
    }
}
