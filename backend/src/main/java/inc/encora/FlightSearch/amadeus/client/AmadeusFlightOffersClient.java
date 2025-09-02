package inc.encora.FlightSearch.amadeus.client;

import inc.encora.FlightSearch.amadeus.constants.common.AmadeusWebClientType;
import inc.encora.FlightSearch.amadeus.dto.common.AmadeusFlightOfferDTO;
import inc.encora.FlightSearch.amadeus.dto.request.AmadeusSearchFlightsRequestDTO;
import inc.encora.FlightSearch.amadeus.dto.response.AmadeusListResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class AmadeusFlightOffersClient {
    private final WebClient webClient;

    public AmadeusFlightOffersClient(@Qualifier(AmadeusWebClientType.WITH_CREDENTIALS) WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<AmadeusListResponseDTO<AmadeusFlightOfferDTO>> searchFlightOffers(
            AmadeusSearchFlightsRequestDTO requestDTO) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/v2/shopping/flight-offers")
                        .queryParam("originLocationCode", requestDTO.getOriginLocationCode())
                        .queryParam("destinationLocationCode", requestDTO.getDestinationLocationCode())
                        .queryParam("departureDate", requestDTO.getDepartureDate())
                        .queryParam("returnDate", requestDTO.getReturnDate())
                        .queryParam("adults", requestDTO.getAdults())
                        .queryParam("nonStop", requestDTO.isNonStop())
                        .queryParam("max", requestDTO.getMax())
                        .queryParam("currencyCode", requestDTO.getCurrencyCode())
                        .build())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }
}
