package inc.encora.FlightSearch.amadeus.client;

import inc.encora.FlightSearch.amadeus.client.fixtures.AmadeusClientTestFixtures;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusFlightOfferDTO;
import inc.encora.FlightSearch.amadeus.dto.request.AmadeusSearchFlightsRequestDTO;
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
class AmadeusFlightOffersClientTest {

    @Mock
    private WebClient mockWebClient;

    @Mock
    private WebClient.RequestHeadersUriSpec mockRequestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec mockRequestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec mockResponseSpec;

    private AmadeusFlightOffersClient flightOffersClient;

    @BeforeEach
    void setUp() {
        flightOffersClient = new AmadeusFlightOffersClient(mockWebClient);
    }

    @Test
    void shouldSearchFlightOffersSuccessfully() {
        // Given
        AmadeusSearchFlightsRequestDTO request = AmadeusClientTestFixtures.createFlightSearchRequest();
        AmadeusListResponseDTO<AmadeusFlightOfferDTO> expectedResponse = AmadeusClientTestFixtures.createFlightOffersResponse();

        // Mock WebClient chain
        when(mockWebClient.get()).thenReturn(mockRequestHeadersUriSpec);
        when(mockRequestHeadersUriSpec.uri(any(Function.class))).thenReturn(mockRequestHeadersSpec);
        when(mockRequestHeadersSpec.retrieve()).thenReturn(mockResponseSpec);
        when(mockResponseSpec.bodyToMono(any(ParameterizedTypeReference.class)))
                .thenReturn(Mono.just(expectedResponse));

        // When
        AmadeusListResponseDTO<AmadeusFlightOfferDTO> result = flightOffersClient.searchFlightOffers(request).block();

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getData()).hasSize(2);
        assertThat(result.getData()).allSatisfy((input -> assertThat(input).isInstanceOf(AmadeusFlightOfferDTO.class)));
        assertThat(result.getMeta().getCount()).isEqualTo(2);
        assertThat(result.getData().get(0).getPrice().getTotal()).isEqualTo("512.85");
        assertThat(result.getData().get(1).getPrice().getTotal()).isEqualTo("789.23");
    }

    @Test
    void shouldHandleEmptyFlightOffersResponse() {
        // Given
        AmadeusSearchFlightsRequestDTO request = AmadeusClientTestFixtures.createFlightSearchRequest();

        // Mock WebClient chain to return empty
        when(mockWebClient.get()).thenReturn(mockRequestHeadersUriSpec);
        when(mockRequestHeadersUriSpec.uri(any(Function.class))).thenReturn(mockRequestHeadersSpec);
        when(mockRequestHeadersSpec.retrieve()).thenReturn(mockResponseSpec);
        when(mockResponseSpec.bodyToMono(any(ParameterizedTypeReference.class))).thenReturn(Mono.empty());

        // When
        AmadeusListResponseDTO<AmadeusFlightOfferDTO> result = flightOffersClient.searchFlightOffers(request).block();

        // Then
        assertThat(result).isNull();
    }

    @Test
    void shouldHandleErrorFromFlightOffersEndpoint() {
        // Given
        AmadeusSearchFlightsRequestDTO request = AmadeusClientTestFixtures.createFlightSearchRequest();
        AmadeusAPIException expectedError = new AmadeusAPIException("Flight service unavailable");

        // Mock WebClient chain to return error
        when(mockWebClient.get()).thenReturn(mockRequestHeadersUriSpec);
        when(mockRequestHeadersUriSpec.uri(any(Function.class))).thenReturn(mockRequestHeadersSpec);
        when(mockRequestHeadersSpec.retrieve()).thenReturn(mockResponseSpec);
        when(mockResponseSpec.bodyToMono(any(ParameterizedTypeReference.class)))
                .thenReturn(Mono.error(expectedError));

        assertThrows(AmadeusAPIException.class, () -> flightOffersClient.searchFlightOffers(request).block());
    }
}
